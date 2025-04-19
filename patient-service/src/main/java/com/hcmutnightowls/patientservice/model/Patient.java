package com.hcmutnightowls.patientservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private LocalDate dateOfBirth;

    private String gender;

    private String nationalId;

    private String bloodType;

    private LocalDate registrationDate;

    private boolean isActive = true;
}
