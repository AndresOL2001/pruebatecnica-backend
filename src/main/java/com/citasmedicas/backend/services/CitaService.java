package com.citasmedicas.backend.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.citasmedicas.backend.DTOs.CrearCitaDTO;
import com.citasmedicas.backend.entity.Cita;
import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;
import com.citasmedicas.backend.helpers.CitaCreacionHelper;
import com.citasmedicas.backend.helpers.Mapper;
import com.citasmedicas.backend.repository.CitaRepository;
import com.citasmedicas.backend.services.interfaces.ICitaService;

@Service
public class CitaService implements ICitaService{
    private final CitaRepository citaRepository;
    private final CitaCreacionHelper citaCreacionHelper;
    private final Mapper mapper;

    public CitaService(CitaRepository citaRepository, Mapper mapper,CitaCreacionHelper citaCreacionHelper) {
        this.citaRepository = citaRepository;
        this.mapper = mapper;
        this.citaCreacionHelper = citaCreacionHelper;
    }

    public List<Cita> obtenerCitas(){
        return citaRepository.findAll();
    }

    @Override
    public Cita guardarCita(CrearCitaDTO crearCitaDTO) {

        Optional<String> resultado = citaCreacionHelper.verificarRestricciones(crearCitaDTO);
        if(resultado.isEmpty()){
            return citaRepository.save(mapper.crearCitaDTOtoCita(crearCitaDTO));
        }else{
            throw new RuntimeException("Error: "+ resultado.get());
        }

        
    }

    @Override
    public List<Cita> obtenerCitasPorFechaYDoctor(LocalDateTime fecha, Doctor doctor) {
       return citaRepository.findByFechaInicioAndDoctor(fecha, doctor);
    }

    @Override
    public List<Cita> obtenerCitasPorFechaYConsultorio(LocalDateTime fecha, Consultorio consultorio) {
        return citaRepository.findByFechaInicioAndConsultorio(fecha, consultorio);
    }

    @Override
    public void cancelarCita(UUID citaId) {
       Optional<Cita> citaOptional = citaRepository.findById(citaId);

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();

            // Verificar si la cita está pendiente de realizarse (según su horario)
            LocalDateTime ahora = LocalDateTime.now();
            if (cita.getFechaInicio().isAfter(ahora)) {
                citaRepository.delete(cita);

            } else {
                throw new IllegalArgumentException("No se puede cancelar una cita que ya ha comenzado.");
            }
        } else {
            throw new NoSuchElementException("Cita no encontrada con el ID proporcionado: " + citaId);
        }
    }
}
