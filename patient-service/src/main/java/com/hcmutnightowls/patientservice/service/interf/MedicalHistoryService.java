package com.hcmutnightowls.patientservice.service.interf;

import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.MedicalHistoryDTO;
import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.CreateMedicalHistoryDTO;
import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.MedicalHistoryResponseDTO;
import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.UpdateMedicalHistoryDTO;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.MedicalHistoryNotFoundException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;

import java.util.List;

public interface MedicalHistoryService {
    MedicalHistoryResponseDTO addMedicalHistory(Long patientId, CreateMedicalHistoryDTO createMedicalHistoryDTO)
            throws PatientNotFoundException, InvalidDataException;
    MedicalHistoryResponseDTO updateMedicalHistory(Long patientId, Long historyId, UpdateMedicalHistoryDTO updateMedicalHistoryDTO)
            throws PatientNotFoundException, MedicalHistoryNotFoundException, InvalidDataException;
    List<MedicalHistoryResponseDTO> getMedicalHistories(Long patientId, String startDate, String endDate)
            throws PatientNotFoundException;
    MedicalHistoryResponseDTO getMedicalHistoryById(Long patientId, Long historyId)
            throws PatientNotFoundException, MedicalHistoryNotFoundException;
    void deleteMedicalHistory(Long patientId, Long historyId)
            throws PatientNotFoundException, MedicalHistoryNotFoundException;
    boolean validateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) throws InvalidDataException;
}
