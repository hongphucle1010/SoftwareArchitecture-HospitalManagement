package com.hcmutnightowls.patientservice.dto.medicalHistoryDto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryQueryDTO {
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Start date must be in the format yyyy-MM-dd")
    private String startDate;
    
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "End date must be in the format yyyy-MM-dd")
    private String endDate;
} 