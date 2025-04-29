package com.hcmutnightowls.patientservice.dto.medicalHistoryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryResponseDTO {
    private Long id;
    private Long patientId;
    private String diagnosis;
    private String treatment;
    private String medication;
    private String allergies;
    private String visitDate;
    private Long doctorId;
    private String notes;
} 