package com.hcmutnightowls.authenticationservice.service;

import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public List<Admin> getAll() {
        return adminRepo.findAll();
    }

    public Optional<Admin> findBySubject(String Subject) {
        return adminRepo.findBySubject(Subject);
    }
}