package com.hcmutnightowls.authenticationservice.service.Interface.Admin;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.request.requestRegister;
import com.hcmutnightowls.authenticationservice.model.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    public List<Admin> getAll();
    public Optional<Admin> findBySubject(String Subject);
    public Admin postAdmin(requestRegister admin);
}
