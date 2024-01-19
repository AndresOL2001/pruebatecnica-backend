package com.citasmedicas.backend.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(schema = "citasmedicas", name = "doctores")
public class Doctor {

  @Id
  private UUID id;
  private String nombre;
  private String apellido_paterno;
  private String apellido_materno;
  private String especialidad;

  //solo eager por prueba normalmente es lazy y se obtiene de forma perezoza
  @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Cita> citas = new HashSet<>();

  public Doctor(UUID id) {
    this.id = id;
  }
}
