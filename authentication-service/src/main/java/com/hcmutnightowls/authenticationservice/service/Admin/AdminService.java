package com.hcmutnightowls.authenticationservice.service.Admin;

import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.repository.AdminRepo;
import com.hcmutnightowls.authenticationservice.service.Interface.Admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Override
    public List<Admin> getAll() {
        return adminRepo.findAll();
    }

    @Override
    public Optional<Admin> findBySubject(String Subject) {
        return adminRepo.findBySubject(Subject);
    }
}