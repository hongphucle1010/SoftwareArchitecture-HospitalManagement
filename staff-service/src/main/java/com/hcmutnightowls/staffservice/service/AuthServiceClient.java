package com.hcmutnightowls.staffservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcmutnightowls.staffservice.dto.AuthenClientRequest;
import com.hcmutnightowls.staffservice.dto.ResponseObject;

@FeignClient(name = "authentication-service")
public interface AuthServiceClient {
    @PostMapping("/api/staff")
    ResponseObject<Void> postStaff(@RequestBody AuthenClientRequest authenClientRequest);
}