package com.hcmutnightowls.authenticationservice.service.Staff;

import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.repository.StaffRepo;
import com.hcmutnightowls.authenticationservice.service.Interface.Staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepo StaffRepo;

    @Override
    public Optional<Staff> findBySubject(String subject) {
        return StaffRepo.findBySubject(subject);
    }
}
