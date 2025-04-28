package com.hcmutnightowls.authenticationservice.service.Admin;

import com.hcmutnightowls.authenticationservice.model.Admin;
import com.hcmutnightowls.authenticationservice.service.Interface.Admin.IAdminService;
import com.hcmutnightowls.authenticationservice.service.Interface.Strategy.UserFinderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("admin_finder")
public class AdminFinder implements UserFinderStrategy {
    @Autowired
    private IAdminService iadminService;

    @Override
    public Admin findBySubject(String subject) {
        return iadminService.findBySubject(subject)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }
}
