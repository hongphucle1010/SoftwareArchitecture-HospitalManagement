package com.hcmutnightowls.patientservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medical_histories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private String diagnosis;

    private String treatment;

    private String medication;

    private String allergies;

    private LocalDate visitDate;

    private Long doctorId;

    private String notes;

//    @ManyToOne
//    @JoinColumn(name = "patient_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Patient patient;
}
