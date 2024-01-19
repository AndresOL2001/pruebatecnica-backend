package com.citasmedicas.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citasmedicas.backend.entity.Cita;
import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;

@Repository
public interface CitaRepository extends JpaRepository<Cita,UUID>{
    List<Cita> findByFechaInicioAndDoctor(LocalDateTime fecha, Doctor doctor);
    List<Cita> findByFechaInicioAndConsultorio(LocalDateTime fecha, Consultorio consultorio);

}
