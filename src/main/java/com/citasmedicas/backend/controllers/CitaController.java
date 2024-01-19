package com.citasmedicas.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citasmedicas.backend.DTOs.CrearCitaDTO;
import com.citasmedicas.backend.entity.Cita;
import com.citasmedicas.backend.services.CitaService;

@Controller
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173") // SOLO POR SER PRUEBA ESTO SE MANEJA CON UN WEBSECURITYCONFIG PARA CORS
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping()
    public ResponseEntity<?> crearCita(@RequestBody CrearCitaDTO crearCitaDTO){
        
        try {
            Cita cita = citaService.guardarCita(crearCitaDTO);
            return new ResponseEntity<>(cita, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarCita(@PathVariable String id){
        
        try {
            citaService.cancelarCita(UUID.fromString(id));
            return new ResponseEntity<>("Cita Cancelada Correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    
    @GetMapping()
    public ResponseEntity<?> obtenerCitas(){
        
        try {
           List<Cita> citas =  citaService.obtenerCitas();
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
