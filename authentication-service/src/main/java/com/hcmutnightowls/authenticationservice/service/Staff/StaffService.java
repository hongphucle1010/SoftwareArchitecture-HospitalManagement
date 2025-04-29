package com.hcmutnightowls.authenticationservice.service.Staff;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.repository.StaffRepo;
import com.hcmutnightowls.authenticationservice.service.Interface.Staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepo StaffRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Staff> findBySubject(String subject) {
        return StaffRepo.findBySubject(subject);
    }

    @Override
    public Staff postStaff(request req) {
        Staff staff = new Staff();
        staff.setSubject(req.getSubject());
        staff.setPassword(passwordEncoder.encode(req.getPassword()));
        staff.setRole("STAFF");
        return StaffRepo.save(staff);
    }
}
