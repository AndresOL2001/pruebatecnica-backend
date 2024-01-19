package com.citasmedicas.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citasmedicas.backend.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,UUID>{

}
