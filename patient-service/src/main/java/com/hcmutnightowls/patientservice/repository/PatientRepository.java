package com.hcmutnightowls.patientservice.repository;

import com.hcmutnightowls.patientservice.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNationalId(String nationalId);
    boolean existsByEmail(String email);
    Page<Patient> findByIsActiveTrue(Pageable pageable);
    Page<Patient> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);
}
