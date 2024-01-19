package com.citasmedicas.backend.entity;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
@Table(schema = "citasmedicas",name="consultorios")
public class Consultorio {

    @Id
    private UUID id;
    
    private String piso;

    private String numeroDeConsultorio;

    @OneToMany(mappedBy = "consultorio",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Cita> citas = new HashSet<>();

    public Consultorio(UUID id) {
        this.id = id;
    }
}
