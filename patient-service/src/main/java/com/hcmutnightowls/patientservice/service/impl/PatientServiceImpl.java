package com.hcmutnightowls.patientservice.service.impl;

import com.hcmutnightowls.patientservice.dto.patientDto.PatientDTO;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.model.Patient;
import com.hcmutnightowls.patientservice.repository.PatientRepository;
import com.hcmutnightowls.patientservice.service.interf.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public PatientDTO registerPatient(PatientDTO patientDTO) throws InvalidDataException {
        if (!validatePatientData(patientDTO)) {
            throw new InvalidDataException("Invalid patient data");
        }
        Patient patient = toEntity(patientDTO);
        patient = patientRepository.save(patient);
        return toDTO(patient);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws PatientNotFoundException, InvalidDataException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        if (!validatePatientData(patientDTO)) {
            throw new InvalidDataException("Invalid patient data");
        }
        updateEntity(patient, patientDTO);
        patient = patientRepository.save(patient);
        return toDTO(patient);
    }

    @Override
    public PatientDTO partialUpdatePatient(Long id, Map<String, Object> fields)
            throws PatientNotFoundException, InvalidDataException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        updateEntityFields(patient, fields);
        patient = patientRepository.save(patient);
        return toDTO(patient);
    }

    @Override
    public void deactivatePatient(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        patient.setIsActive(false);
        patientRepository.save(patient);
    }

    @Override
    public PatientDTO reactivatePatient(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        patient.setIsActive(true);
        patient = patientRepository.save(patient);
        return toDTO(patient);
    }

    @Override
    public boolean validatePatientData(PatientDTO patientDTO) throws InvalidDataException {
        if (patientDTO.getFullName() == null || patientDTO.getEmail() == null || patientDTO.getRegistrationDate() == null) {
            return false;
        }
        if (!patientDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }
        return true;
    }

    private Patient toEntity(PatientDTO dto) {
        return Patient.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .dateOfBirth(dto.getDateOfBirth() != null ? LocalDate.parse(dto.getDateOfBirth()) : null)
                .gender(dto.getGender())
                .nationalId(dto.getNationalId())
                .bloodType(dto.getBloodType())
                .registrationDate(LocalDate.parse(dto.getRegistrationDate()))
                .isActive(dto.isActive())
                .build();
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

    private void updateEntity(Patient patient, PatientDTO dto) {
        patient.setFullName(dto.getFullName());
        patient.setEmail(dto.getEmail());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
        patient.setDateOfBirth(dto.getDateOfBirth() != null ? LocalDate.parse(dto.getDateOfBirth()) : null);
        patient.setGender(dto.getGender());
        patient.setNationalId(dto.getNationalId());
        patient.setBloodType(dto.getBloodType());
        patient.setRegistrationDate(LocalDate.parse(dto.getRegistrationDate()));
        patient.setIsActive(dto.isActive());
    }

    private void updateEntityFields(Patient patient, Map<String, Object> fields) throws InvalidDataException {
        fields.forEach((key, value) -> {
            switch (key) {
                case "fullName" -> patient.setFullName((String) value);
                case "email" -> patient.setEmail((String) value);
                case "phoneNumber" -> patient.setPhoneNumber((String) value);
                case "address" -> patient.setAddress((String) value);
                case "dateOfBirth" -> patient.setDateOfBirth(value != null ? LocalDate.parse((String) value) : null);
                case "gender" -> patient.setGender((String) value);
                case "nationalId" -> patient.setNationalId((String) value);
                case "bloodType" -> patient.setBloodType((String) value);
                case "registrationDate" -> patient.setRegistrationDate(LocalDate.parse((String) value));
                case "isActive" -> patient.setIsActive((Boolean) value);
                default -> throw new InvalidDataException("Invalid field: " + key);
            }
        });
    }
}
