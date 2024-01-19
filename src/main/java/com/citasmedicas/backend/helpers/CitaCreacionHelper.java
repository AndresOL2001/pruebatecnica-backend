package com.citasmedicas.backend.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.citasmedicas.backend.DTOs.CrearCitaDTO;
import com.citasmedicas.backend.entity.Cita;
import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;
import com.citasmedicas.backend.repository.CitaRepository;

@Component
public class CitaCreacionHelper {
    private final CitaRepository citaRepository;

    public CitaCreacionHelper(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public Optional<String> verificarRestricciones(CrearCitaDTO nuevaCita) {
        LocalDateTime inicioNuevaCita = parseFecha(nuevaCita.getInicioConsulta());
        LocalDateTime finNuevaCita = parseFecha(nuevaCita.getInicioConsulta()).plusHours(2);

        List<Cita> citasExistentes = obtenerCitas(nuevaCita);

        // Verificar restricciones
        for (Cita citaExistente : citasExistentes) {
            LocalDateTime inicioExistente = citaExistente.getFechaInicio();
            LocalDateTime finExistente = citaExistente.getFechaFin();

            // Verificar si hay superposición de horarios o si la diferencia es menor a 2
            // horas
            if ((inicioNuevaCita.isBefore(finExistente) && finNuevaCita.isAfter(inicioExistente))
                    || inicioNuevaCita.minusHours(2).isBefore(finExistente)) {

                // Verificar si la nueva cita y la cita existente están en el mismo consultorio
                if (citaExistente.getConsultorio().getId().equals(UUID.fromString(nuevaCita.getConsultorio()))) {
                    return Optional.of("No se puede agendar cita en el mismo consultorio a la misma hora.");
                }

                if (citaExistente.getNombrePaciente().equals(nuevaCita.getNombrePaciente()) &&
                        inicioNuevaCita.toLocalDate().isEqual(inicioExistente.toLocalDate())) {

                    return Optional.of(
                            "No se puede agendar otra cita para el mismo paciente el mismo día a la misma hora o con menos de 2 horas de diferencia.");
                }

            }
        }

        // Verificar que un mismo doctor no tenga más de 8 citas en el día
        long citasParaDoctorEnElDia = citasExistentes.stream()
                .filter(cita -> cita.getDoctor().getId().equals(UUID.fromString(nuevaCita.getDoctor())))
                .count();

        if (citasParaDoctorEnElDia >= 8) {
            return Optional.of("Un mismo doctor no puede tener más de 8 citas en el día.");
        }

        return Optional.empty();
    }

    private List<Cita> obtenerCitas(CrearCitaDTO nuevaCita) {
        // Obtener citas existentes para el mismo paciente y día
        List<Cita> citasConsultorios = citaRepository.findByFechaInicioAndConsultorio(
                parseFecha(nuevaCita.getInicioConsulta()),
                new Consultorio(UUID.fromString(nuevaCita.getConsultorio())));
        List<Cita> citasDoctores = citaRepository.findByFechaInicioAndDoctor(parseFecha(nuevaCita.getInicioConsulta()),
                new Doctor(UUID.fromString(nuevaCita.getDoctor())));
        List<Cita> todasLasCitas = new ArrayList<>();
        todasLasCitas.addAll(citasConsultorios);
        todasLasCitas.addAll(citasDoctores);

        return todasLasCitas.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private LocalDateTime parseFecha(String fecha) {
        return LocalDateTime.parse(fecha);
    }

}
