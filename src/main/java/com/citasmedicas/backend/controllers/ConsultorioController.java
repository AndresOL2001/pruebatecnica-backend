package com.citasmedicas.backend.controllers;

import com.citasmedicas.backend.services.ConsultorioService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.backend.entity.Consultorio;
@RestController
@RequestMapping("/api/consultorios")
@CrossOrigin(origins = "http://localhost:5173") // SOLO POR SER PRUEBA ESTO SE MANEJA CON UN WEBSECURITYCONFIG PARA CORS
public class ConsultorioController {
    
    private final ConsultorioService consultorioService;
    public ConsultorioController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }


    @GetMapping()
    public ResponseEntity<?> obtenerConsultorios(){

        List<Consultorio> consultorios = consultorioService.obtenerConsultorios();

        return new ResponseEntity<>(consultorios, HttpStatus.OK);
    }

}