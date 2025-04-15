package com.hcmutnightowls.staffservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workloads")
public class Workload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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