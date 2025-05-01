package com.hcmutnightowls.patientservice.dto.patientDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenDTO {
    private Long id;
    private String subject;
    private String password;
}
