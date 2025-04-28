package com.hcmutnightowls.authenticationservice.service.Staff;

import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.service.Interface.Staff.IStaffService;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserFinderStrategy;
import com.hcmutnightowls.authenticationservice.service.Patient.PatientService;
import com.hcmutnightowls.authenticationservice.service.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("staff_finder")
public class StaffFinder implements UserFinderStrategy {
    @Autowired
    private IStaffService istaffService;

    @Override
    public Staff findBySubject(String subject) {
        return istaffService.findBySubject(subject)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }
}
