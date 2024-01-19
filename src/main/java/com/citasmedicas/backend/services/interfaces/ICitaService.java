package com.citasmedicas.backend.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.citasmedicas.backend.DTOs.CrearCitaDTO;
import com.citasmedicas.backend.entity.Cita;
import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;

public interface ICitaService {
    // ALTAS
    public Cita guardarCita(CrearCitaDTO citaCreacionDTO);

    // CONSULTAS
    public List<Cita> obtenerCitasPorFechaYDoctor(LocalDateTime fecha, Doctor doctor);

    public List<Cita> obtenerCitasPorFechaYConsultorio(LocalDateTime fecha, Consultorio consultorio);

    public void cancelarCita(UUID citaId);

}
