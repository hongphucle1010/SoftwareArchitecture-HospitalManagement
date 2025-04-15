package com.hcmutnightowls.staffservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String gender;
    
    @Enumerated(EnumType.STRING)
    private StaffType staffType; // DOCTOR, NURSE, ADMIN, etc.
    
    private String department;
    private String position;
    
    @ElementCollection
    @CollectionTable(name = "staff_qualifications", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "qualification")
    private List<String> qualifications;
    
    @ElementCollection
    @CollectionTable(name = "staff_specializations", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "specialization")
    private List<String> specializations;
    
    private LocalDate joiningDate;
    private boolean isActive;
}