package com.hcmutnightowls.patientservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patient_insurance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private String insuranceProvider;

    private String policyNumber;

    private LocalDate validFrom;

    private LocalDate validUntil;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Patient patient;
}
