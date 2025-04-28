package com.hcmutnightowls.authenticationservice.service.Patient;

import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.service.Interface.Patient.IPatientService;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserFinderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("patient_finder")
public class PatientFinder implements UserFinderStrategy {
    @Autowired
    private IPatientService ipatientService;

    @Override
    public Patient findBySubject(String subject) {
        return ipatientService.findBySubject(subject)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
