package com.hcmutnightowls.patientservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private Long id;
    private String subject;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private String gender;
    private String nationalId;
    private String bloodType;
    private String registrationDate;
    private boolean isActive;
}
