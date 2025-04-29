package com.hcmutnightowls.patientservice.dto.medicalHistoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDTO {
    private Long id;
    
    @NotNull(message = "Patient ID must not be null")
    private Long patientId;
    
    @NotBlank(message = "Diagnosis must not be blank")
    @Size(max = 500, message = "Diagnosis must be at most 500 characters")
    private String diagnosis;
    
    @NotBlank(message = "Treatment must not be blank")
    @Size(max = 500, message = "Treatment must be at most 500 characters")
    private String treatment;
    
    @Size(max = 500, message = "Medication must be at most 500 characters")
    private String medication;
    
    @Size(max = 500, message = "Allergies must be at most 500 characters")
    private String allergies;
    
    @NotBlank(message = "Visit date must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Visit date must be in the format yyyy-MM-dd")
    private String visitDate;
    
    @NotNull(message = "Doctor ID must not be null")
    private Long doctorId;
    
    @Size(max = 1000, message = "Notes must be at most 1000 characters")
    private String notes;
} 