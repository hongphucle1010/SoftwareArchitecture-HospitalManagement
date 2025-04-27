package com.hcmutnightowls.patientservice.dto.insuranceDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInsuranceDTO {
    @NotNull(message = "Patient ID must not be null")
    private Long patientId;

    @NotBlank(message = "Insurance provider must not be blank")
    @Size(max = 255, message = "Insurance provider must be at most 255 characters")
    private String insuranceProvider;

    @NotBlank(message = "Policy number must not be blank")
    @Size(max = 50, message = "Policy number must be at most 50 characters")
    private String policyNumber;

    @NotBlank(message = "Valid from date must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Valid from date must be in the format yyyy-MM-dd")
    private String validFrom;

    @NotBlank(message = "Valid until date must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Valid until date must be in the format yyyy-MM-dd")
    private String validUntil;
} 