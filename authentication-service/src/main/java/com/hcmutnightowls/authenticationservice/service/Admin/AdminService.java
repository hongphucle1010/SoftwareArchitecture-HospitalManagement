package com.hcmutnightowls.authenticationservice.service.Admin;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.repository.AdminRepo;
import com.hcmutnightowls.authenticationservice.service.Interface.Admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Admin> getAll() {
        return adminRepo.findAll();
    }

    @Override
    public Optional<Admin> findBySubject(String Subject) {
        return adminRepo.findBySubject(Subject);
    }
    @Override
    public Admin postAdmin(request Req) {
        Admin admin = new Admin();
        admin.setSubject(Req.getSubject());
        admin.setPassword(passwordEncoder.encode(Req.getPassword()));
        admin.setRole("ADMIN");
        return adminRepo.save(admin);
    }
}