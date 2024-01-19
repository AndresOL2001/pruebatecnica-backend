package com.citasmedicas.backend.services.interfaces;

import com.citasmedicas.backend.DTOs.CrearDoctorDTO;
import com.citasmedicas.backend.entity.Doctor;

public interface IDoctorService {

    public Doctor guardarDoctor (CrearDoctorDTO crearDoctorDTO);
    
    
}
