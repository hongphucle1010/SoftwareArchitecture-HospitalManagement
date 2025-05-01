package com.hcmutnightowls.patientservice.service.interf;

import com.hcmutnightowls.patientservice.dto.patientDto.PatientDTO;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;

import java.util.List;

public interface PatientQueryService {
    PatientDTO getPatientById(Long id) throws PatientNotFoundException;
    List<PatientDTO> getAllPatients(int page, int size, String search);
    PatientDTO getPatientByNationalId(String nationalId) throws PatientNotFoundException;
    List<PatientDTO> getActivePatients(int page, int size);
    boolean existsByEmail(String email);
}
