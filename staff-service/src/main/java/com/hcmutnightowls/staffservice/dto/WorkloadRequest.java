package com.hcmutnightowls.staffservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkloadRequest {
    private Long staffId;
    private LocalDate date;
    private int patientCount;
    private int appointmentCount;
    private int procedureCount;
    private int surgeryCount;
    private int consultationCount;
    private double hoursWorked;
    private String notes;
}