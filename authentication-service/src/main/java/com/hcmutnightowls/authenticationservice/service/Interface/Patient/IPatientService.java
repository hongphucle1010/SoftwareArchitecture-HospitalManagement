package com.hcmutnightowls.authenticationservice.service.Interface.Patient;


import com.hcmutnightowls.authenticationservice.model.Patient;

import java.util.Optional;

public interface IPatientService {
    public Optional<Patient> findBySubject(String Subject);
}
