package com.hcmutnightowls.patientservice.service.impl;

import com.hcmutnightowls.patientservice.dto.PatientDTO;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.model.Patient;
import com.hcmutnightowls.patientservice.repository.PatientRepository;
import com.hcmutnightowls.patientservice.service.interf.PatientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientQueryServiceImpl implements PatientQueryService {
    private final PatientRepository patientRepository;

    @Override
    public PatientDTO getPatientById(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        return toDTO(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients(int page, int size, String search) {
        if (search != null && !search.isEmpty()) {
            return patientRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                            search, search, PageRequest.of(page, size))
                    .stream().map(this::toDTO).collect(Collectors.toList());
        }
        return patientRepository.findAll(PageRequest.of(page, size))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientByNationalId(String nationalId) throws PatientNotFoundException {
        Patient patient = patientRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with national ID: " + nationalId));
        return toDTO(patient);
    }

    @Override
    public List<PatientDTO> getActivePatients(int page, int size) {
        return patientRepository.findByIsActiveTrue(PageRequest.of(page, size))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    private PatientDTO toDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth() != null ? patient.getDateOfBirth().toString() : null)
                .gender(patient.getGender())
                .nationalId(patient.getNationalId())
                .bloodType(patient.getBloodType())
                .registrationDate(patient.getRegistrationDate().toString())
                .isActive(patient.getIsActive())
                .build();
    }
}
