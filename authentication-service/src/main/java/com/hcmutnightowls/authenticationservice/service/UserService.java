package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.model.Patient;
import com.hcmutnightowls.authenticationservice.model.User;
import com.hcmutnightowls.authenticationservice.repository.PatientRepo;
import com.hcmutnightowls.authenticationservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Optional<User> findBySubject(String subject) {
        return userRepo.findBySubject(subject);
    }
}
