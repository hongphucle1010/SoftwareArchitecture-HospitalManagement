package com.hcmutnightowls.staffservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenClientRequest {
    private Long id;
    private String subject;
    private String password;
}
