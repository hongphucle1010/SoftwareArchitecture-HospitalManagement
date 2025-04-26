package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.repository.PatientRepo;
import com.hcmutnightowls.authenticationservice.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffRepo StaffRepo;
    public Optional<Staff> findBySubject(String subject) {
        return StaffRepo.findBySubject(subject);
    }
}
