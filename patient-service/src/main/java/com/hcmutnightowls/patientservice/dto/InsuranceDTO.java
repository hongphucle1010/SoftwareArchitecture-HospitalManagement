package com.hcmutnightowls.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDTO {
    private Long id;
    private Long patientId;
    private String insuranceProvider;
    private String policyNumber;
    private String validFrom;
    private String validUntil;
}
