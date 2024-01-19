package com.citasmedicas.backend.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearCitaDTO {

    private String consultorio;

    private String doctor;

    private String inicioConsulta;

    private String nombrePaciente;

}
