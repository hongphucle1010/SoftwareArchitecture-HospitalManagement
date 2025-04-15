package com.hcmutnightowls.staffservice.dto;

import com.hcmutnightowls.staffservice.model.StaffType;
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
public class StaffRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String gender;
    private StaffType staffType;
    private String department;
    private String position;
    private List<String> qualifications;
    private List<String> specializations;
    private LocalDate joiningDate;
    private boolean isActive;
}