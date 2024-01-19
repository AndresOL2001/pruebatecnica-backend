package com.citasmedicas.backend.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearDoctorDTO {
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String especialidad;
}
