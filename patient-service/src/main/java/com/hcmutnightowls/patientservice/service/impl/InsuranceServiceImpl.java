package com.hcmutnightowls.patientservice.service.impl;

import com.hcmutnightowls.patientservice.dto.insuranceDto.CreateInsuranceDTO;
import com.hcmutnightowls.patientservice.dto.insuranceDto.InsuranceDTO;
import com.hcmutnightowls.patientservice.dto.insuranceDto.UpdateInsuranceDTO;
import com.hcmutnightowls.patientservice.exception.InsuranceNotFoundException;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.model.PatientInsurance;
import com.hcmutnightowls.patientservice.repository.PatientInsuranceRepository;
import com.hcmutnightowls.patientservice.repository.PatientRepository;
import com.hcmutnightowls.patientservice.service.interf.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final PatientRepository patientRepository;
    private final PatientInsuranceRepository insuranceRepository;

    @Override
    public InsuranceDTO addInsurance(CreateInsuranceDTO createInsuranceDTO)
            throws PatientNotFoundException, InvalidDataException {
        
        Long patientId = createInsuranceDTO.getPatientId();
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        
        // Chuyển đổi CreateInsuranceDTO sang InsuranceDTO để validate
        InsuranceDTO insuranceDTO = InsuranceDTO.builder()
                .patientId(patientId)
                .insuranceProvider(createInsuranceDTO.getInsuranceProvider())
                .policyNumber(createInsuranceDTO.getPolicyNumber())
                .validFrom(createInsuranceDTO.getValidFrom())
                .validUntil(createInsuranceDTO.getValidUntil())
                .build();
                
        if (!validateInsurance(insuranceDTO)) {
            throw new InvalidDataException("Invalid insurance data");
        }
        
        PatientInsurance insurance = toEntity(createInsuranceDTO);
        insurance.setPatientId(patientId);
        insurance = insuranceRepository.save(insurance);
        return toDTO(insurance);
    }

    @Override
    public InsuranceDTO updateInsurance(Long id, UpdateInsuranceDTO updateInsuranceDTO)
            throws PatientNotFoundException, InsuranceNotFoundException, InvalidDataException {
                
        Long patientId = updateInsuranceDTO.getPatientId();
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        PatientInsurance insurance = insuranceRepository.findByIdAndPatientId(id, patientId)
                .orElseThrow(() -> new InsuranceNotFoundException("Insurance not found with ID: " + id));
        
        // Chuyển đổi UpdateInsuranceDTO sang InsuranceDTO để validate
        InsuranceDTO insuranceDTO = InsuranceDTO.builder()
                .patientId(patientId)
                .insuranceProvider(updateInsuranceDTO.getInsuranceProvider())
                .policyNumber(updateInsuranceDTO.getPolicyNumber())
                .validFrom(updateInsuranceDTO.getValidFrom())
                .validUntil(updateInsuranceDTO.getValidUntil())
                .build();
                
        if (!validateInsurance(insuranceDTO)) {
            throw new InvalidDataException("Invalid insurance data");
        }
        updateEntity(insurance, insuranceDTO);
        insurance = insuranceRepository.save(insurance);
        return toDTO(insurance);
    }

    @Override
    public List<InsuranceDTO> getInsuranceDetails(Long patientId) throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        return insuranceRepository.findByPatientId(patientId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteInsurance(Long patientId, Long insuranceId)
            throws PatientNotFoundException, InsuranceNotFoundException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        PatientInsurance insurance = insuranceRepository.findByIdAndPatientId(insuranceId, patientId)
                .orElseThrow(() -> new InsuranceNotFoundException("Insurance not found with ID: " + insuranceId));
        insuranceRepository.delete(insurance);
    }

    @Override
    public boolean validateInsurance(InsuranceDTO insuranceDTO) throws InvalidDataException {
        if (insuranceDTO.getValidFrom() != null && insuranceDTO.getValidUntil() != null) {
            try {
                LocalDate from = LocalDate.parse(insuranceDTO.getValidFrom());
                LocalDate until = LocalDate.parse(insuranceDTO.getValidUntil());
                if (from.isAfter(until)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private PatientInsurance toEntity(CreateInsuranceDTO dto) {
        return PatientInsurance.builder()
                .insuranceProvider(dto.getInsuranceProvider())
                .policyNumber(dto.getPolicyNumber())
                .validFrom(dto.getValidFrom() != null ? LocalDate.parse(dto.getValidFrom()) : null)
                .validUntil(dto.getValidUntil() != null ? LocalDate.parse(dto.getValidUntil()) : null)
                .build();
    }

    private PatientInsurance toEntity(InsuranceDTO dto) {
        return PatientInsurance.builder()
                .insuranceProvider(dto.getInsuranceProvider())
                .policyNumber(dto.getPolicyNumber())
                .validFrom(dto.getValidFrom() != null ? LocalDate.parse(dto.getValidFrom()) : null)
                .validUntil(dto.getValidUntil() != null ? LocalDate.parse(dto.getValidUntil()) : null)
                .build();
    }

    private InsuranceDTO toDTO(PatientInsurance insurance) {
        return InsuranceDTO.builder()
                .id(insurance.getId())
                .patientId(insurance.getPatientId())
                .insuranceProvider(insurance.getInsuranceProvider())
                .policyNumber(insurance.getPolicyNumber())
                .validFrom(insurance.getValidFrom() != null ? insurance.getValidFrom().toString() : null)
                .validUntil(insurance.getValidUntil() != null ? insurance.getValidUntil().toString() : null)
                .build();
    }

    private void updateEntity(PatientInsurance insurance, InsuranceDTO dto) {
        insurance.setInsuranceProvider(dto.getInsuranceProvider());
        insurance.setPolicyNumber(dto.getPolicyNumber());
        insurance.setValidFrom(dto.getValidFrom() != null ? LocalDate.parse(dto.getValidFrom()) : null);
        insurance.setValidUntil(dto.getValidUntil() != null ? LocalDate.parse(dto.getValidUntil()) : null);
    }
}
