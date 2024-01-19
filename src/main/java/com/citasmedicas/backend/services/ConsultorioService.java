package com.citasmedicas.backend.services;
import java.util.List;

import org.springframework.stereotype.Service;
import com.citasmedicas.backend.DTOs.CrearConsultorioDTO;
import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;
import com.citasmedicas.backend.helpers.Mapper;
import com.citasmedicas.backend.repository.ConsultorioRepository;
import com.citasmedicas.backend.services.interfaces.IConsultorioService;

@Service
public class ConsultorioService implements IConsultorioService{
    private final ConsultorioRepository consultorioRepository;
    private final Mapper mapper;

    public ConsultorioService(ConsultorioRepository consultorioRepository, Mapper mapper) {
        this.consultorioRepository = consultorioRepository;
        this.mapper = mapper;
    }

    @Override
    public Consultorio guardarConsultorio(CrearConsultorioDTO crearConsultorioDTO) {
        return consultorioRepository.save(mapper.crearConsultorioDTOtoConsultorio(crearConsultorioDTO));
    }

     public List<Consultorio> obtenerConsultorios(){
        return consultorioRepository.findAll();
    }
}
