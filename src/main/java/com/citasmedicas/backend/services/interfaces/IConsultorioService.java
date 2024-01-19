package com.citasmedicas.backend.services.interfaces;


import com.citasmedicas.backend.DTOs.CrearConsultorioDTO;
import com.citasmedicas.backend.entity.Consultorio;

public interface IConsultorioService {
       public Consultorio guardarConsultorio (CrearConsultorioDTO crearDoctorDTO);

}
