package com.hcmutnightowls.authenticationservice.service.Patient;

import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.repository.PatientRepo;
import com.hcmutnightowls.authenticationservice.service.Interface.Patient.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepo patientRepo;
    @Override
    public Optional<Patient> findBySubject(String subject) {
         return patientRepo.findBySubject(subject);
    }
}
