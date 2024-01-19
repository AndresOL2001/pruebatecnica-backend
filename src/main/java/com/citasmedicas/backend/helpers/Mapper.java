package com.citasmedicas.backend.helpers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.citasmedicas.backend.DTOs.CrearCitaDTO;
import com.citasmedicas.backend.DTOs.CrearConsultorioDTO;
import com.citasmedicas.backend.DTOs.CrearDoctorDTO;
import com.citasmedicas.backend.entity.Cita;
import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;

@Component
public class Mapper {

    public Mapper() {
    }

    public Doctor crearDoctorDTOtoDoctor(CrearDoctorDTO crearDoctorDTO) {
        return Doctor.builder()
                .id(UUID.randomUUID())
                .especialidad(crearDoctorDTO.getEspecialidad())
                .apellido_materno(crearDoctorDTO.getApellidoMaterno())
                .apellido_paterno(crearDoctorDTO.getApellidoPaterno())
                .nombre(crearDoctorDTO.getNombre())
                .build();

    }

    public Consultorio crearConsultorioDTOtoConsultorio(CrearConsultorioDTO crearConsultorioDTO) {
        return Consultorio.builder()
                .id(UUID.randomUUID())
                .piso(crearConsultorioDTO.getPiso())
                .numeroDeConsultorio(crearConsultorioDTO.getNumeroConsultorio())
                .build();
    }

    public Cita crearCitaDTOtoCita(CrearCitaDTO crearCitaDTO) {
        return Cita.builder()
        .id(UUID.randomUUID())
        .consultorio(new Consultorio(UUID.fromString(crearCitaDTO.getConsultorio())))
        .nombrePaciente(crearCitaDTO.getNombrePaciente())
        .doctor(new Doctor(UUID.fromString(crearCitaDTO.getDoctor())))
        .fechaInicio(parseFecha(crearCitaDTO.getInicioConsulta()))
        .fechaFin(parseFecha(crearCitaDTO.getInicioConsulta()).plusHours(2))
        .build();
    }

    private LocalDateTime parseFecha(String fecha) {
        return LocalDateTime.parse(fecha);
    }
}
