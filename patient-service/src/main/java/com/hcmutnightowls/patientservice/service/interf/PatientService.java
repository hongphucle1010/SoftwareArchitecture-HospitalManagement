package com.hcmutnightowls.patientservice.service.interf;

import com.hcmutnightowls.patientservice.dto.PatientDTO;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;

import java.util.Map;

public interface PatientService {
    PatientDTO registerPatient(PatientDTO patientDTO) throws InvalidDataException;
    PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws PatientNotFoundException, InvalidDataException;
    PatientDTO partialUpdatePatient(Long id, Map<String, Object> fields) throws PatientNotFoundException, InvalidDataException;
    void deactivatePatient(Long id) throws PatientNotFoundException;
    PatientDTO reactivatePatient(Long id) throws PatientNotFoundException;
    boolean validatePatientData(PatientDTO patientDTO) throws InvalidDataException;
}
