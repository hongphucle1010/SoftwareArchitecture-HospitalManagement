package com.hcmutnightowls.authenticationservice.service.Interface.Staff;

import com.hcmutnightowls.authenticationservice.model.Staff;

import java.util.Optional;

public interface IStaffService {
    public Optional<Staff> findBySubject(String Subject);
}
