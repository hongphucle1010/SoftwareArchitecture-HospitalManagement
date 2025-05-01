package com.hcmutnightowls.authenticationservice.service.Patient;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.request.requestRegister;
import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.repository.PatientRepo;
import com.hcmutnightowls.authenticationservice.service.Interface.Patient.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Patient> findBySubject(String subject) {
         return patientRepo.findBySubject(subject);
    }
    @Override
    public Patient postPatient(requestRegister req) {
        Patient patient = new Patient();
        patient.setId(req.getId());
        patient.setSubject(req.getSubject());
        patient.setPassword(passwordEncoder.encode(req.getPassword()));
        patient.setRole("PATIENT");
        patientRepo.save(patient);
    }
}
