package com.citasmedicas.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citasmedicas.backend.DTOs.CrearDoctorDTO;
import com.citasmedicas.backend.entity.Doctor;
import com.citasmedicas.backend.helpers.Mapper;
import com.citasmedicas.backend.repository.DoctorRepository;
import com.citasmedicas.backend.services.interfaces.IDoctorService;

@Service
public class DoctorService implements IDoctorService {
    private final DoctorRepository doctorRepository;
    private final Mapper mapper;

    public DoctorService(DoctorRepository doctorRepository, Mapper mapper) {
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }

    @Override
    public Doctor guardarDoctor(CrearDoctorDTO crearDoctorDTO) {
        return doctorRepository.save(mapper.crearDoctorDTOtoDoctor(crearDoctorDTO));
    }

    public List<Doctor> obtenerDoctores(){
        return doctorRepository.findAll();
    }
}
