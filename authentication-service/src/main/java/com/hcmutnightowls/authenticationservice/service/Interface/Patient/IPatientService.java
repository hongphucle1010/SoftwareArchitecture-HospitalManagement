package com.hcmutnightowls.authenticationservice.service.Interface.Patient;


import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.request.requestRegister;
import com.hcmutnightowls.authenticationservice.model.Patient;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IPatientService {
    public Optional<Patient> findBySubject(String Subject);
    public Patient postPatient(requestRegister req);
}
