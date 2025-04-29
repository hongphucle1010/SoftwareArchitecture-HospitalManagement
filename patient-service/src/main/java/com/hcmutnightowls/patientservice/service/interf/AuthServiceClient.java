package com.hcmutnightowls.patientservice.service.interf;

import com.hcmutnightowls.patientservice.dto.AuthenDTO;
import com.hcmutnightowls.patientservice.dto.ResponseObject;
import com.hcmutnightowls.patientservice.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service")
public interface AuthServiceClient {
    @PostMapping("/api/patient")
    ResponseObject<Void> postPatient(@RequestBody AuthenDTO authenDTO);
}
