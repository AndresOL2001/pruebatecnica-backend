package com.citasmedicas.backend.config;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.citasmedicas.backend.entity.Consultorio;
import com.citasmedicas.backend.entity.Doctor;
import com.citasmedicas.backend.repository.ConsultorioRepository;
import com.citasmedicas.backend.repository.DoctorRepository;

@Configuration
public class PostConstructInsertData {
    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostConstruct
    public void initTestData() {
        List<Consultorio> consultorios = Arrays.asList(
                Consultorio.builder()
                        .id(UUID.randomUUID())
                        .piso("1")
                        .numeroDeConsultorio("101")
                        .build(),
                Consultorio.builder()
                        .id(UUID.randomUUID())
                        .piso("3")
                        .numeroDeConsultorio("303")
                        .build(),
                Consultorio.builder()
                        .id(UUID.randomUUID())
                        .piso("2")
                        .numeroDeConsultorio("202")
                        .build());

        consultorioRepository.saveAll(consultorios);

        List<Doctor> doctores = Arrays.asList(
                Doctor.builder()
                        .id(UUID.randomUUID())
                        .nombre("Dr. Juan")
                        .apellido_paterno("Perez")
                        .apellido_materno("Gomez")
                        .especialidad("Cardiología")
                        .build(),
                Doctor.builder()
                        .id(UUID.randomUUID())
                        .nombre("Dra. Maria")
                        .apellido_paterno("Lopez")
                        .apellido_materno("García")
                        .especialidad("Pediatria")
                        .build(),
                Doctor.builder()
                        .id(UUID.randomUUID())
                        .nombre("Dr. Jose")
                        .apellido_paterno("Galvez")
                        .apellido_materno("Gonzalez")
                        .especialidad("Pediatria")
                        .build());
        doctorRepository.saveAll(doctores);
    }
}
