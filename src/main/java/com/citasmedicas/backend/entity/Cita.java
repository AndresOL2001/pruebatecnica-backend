package com.citasmedicas.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "citasmedicas",name = "citas")
public class Cita {
    @Id
    private UUID id;

    @ManyToOne(fetch=FetchType.EAGER)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "id_consultorio")
    private Consultorio consultorio;

    @ManyToOne(fetch=FetchType.EAGER)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String nombrePaciente;

}
