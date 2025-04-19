package com.hcmutnightowls.patientservice.service.interf;

import com.hcmutnightowls.patientservice.dto.MedicalHistoryDTO;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.MedicalHistoryNotFoundException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;

import java.util.List;

public interface MedicalHistoryService {
    MedicalHistoryDTO addMedicalHistory(Long patientId, MedicalHistoryDTO medicalHistoryDTO)
            throws PatientNotFoundException, InvalidDataException;
    MedicalHistoryDTO updateMedicalHistory(Long patientId, Long historyId, MedicalHistoryDTO medicalHistoryDTO)
            throws PatientNotFoundException, MedicalHistoryNotFoundException, InvalidDataException;
    List<MedicalHistoryDTO> getMedicalHistories(Long patientId, String startDate, String endDate)
            throws PatientNotFoundException;
    MedicalHistoryDTO getMedicalHistoryById(Long patientId, Long historyId)
            throws PatientNotFoundException, MedicalHistoryNotFoundException;
    void deleteMedicalHistory(Long patientId, Long historyId)
            throws PatientNotFoundException, MedicalHistoryNotFoundException;
    boolean validateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) throws InvalidDataException;
}
