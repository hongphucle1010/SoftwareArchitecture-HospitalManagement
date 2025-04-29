package com.hcmutnightowls.patientservice.service.interf;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "authentication-service")
public interface AuthServiceClient {

}
