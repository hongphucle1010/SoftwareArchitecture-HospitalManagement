package com.hcmutnightowls.authenticationservice.repository;

import com.hcmutnightowls.authenticationservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findBySubject(String subject);
}
