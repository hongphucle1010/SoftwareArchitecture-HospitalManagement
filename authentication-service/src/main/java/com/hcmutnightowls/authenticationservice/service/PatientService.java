package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public Optional<Patient> findBySubject(String subject) {
         return patientRepo.findBySubject(subject);
    }
}
