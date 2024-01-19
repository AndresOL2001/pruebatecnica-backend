package com.citasmedicas.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.backend.entity.Doctor;
import com.citasmedicas.backend.services.DoctorService;

@RestController
@RequestMapping("/api/doctores")
@CrossOrigin(origins = "http://localhost:5173") // SOLO POR SER PRUEBA ESTO SE MANEJA CON UN WEBSECURITYCONFIG PARA CORS
public class DoctorController {
    
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping()
    public ResponseEntity<?> obtenerDoctores(){

        List<Doctor> doctores = doctorService.obtenerDoctores();

        return new ResponseEntity<>(doctores, HttpStatus.OK);
    }

}
