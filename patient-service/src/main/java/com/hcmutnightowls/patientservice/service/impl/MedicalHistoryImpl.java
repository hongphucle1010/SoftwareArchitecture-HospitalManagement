package com.hcmutnightowls.patientservice.service.impl;

import com.hcmutnightowls.patientservice.dto.MedicalHistoryDTO;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.MedicalHistoryNotFoundException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.model.MedicalHistory;
import com.hcmutnightowls.patientservice.repository.MedicalHistoryRepository;
import com.hcmutnightowls.patientservice.repository.PatientRepository;
import com.hcmutnightowls.patientservice.service.interf.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalHistoryImpl implements MedicalHistoryService {
    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public MedicalHistoryDTO addMedicalHistory(Long patientId, MedicalHistoryDTO medicalHistoryDTO)
            throws PatientNotFoundException, InvalidDataException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        if (!validateMedicalHistory(medicalHistoryDTO)) {
            throw new InvalidDataException("Invalid medical history data");
        }
        MedicalHistory history = toEntity(medicalHistoryDTO);
        history.setPatientId(patientId);
        history = medicalHistoryRepository.save(history);
        return toDTO(history);
    }

    @Override
    public MedicalHistoryDTO updateMedicalHistory(Long patientId, Long historyId, MedicalHistoryDTO medicalHistoryDTO)
            throws PatientNotFoundException, MedicalHistoryNotFoundException, InvalidDataException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        MedicalHistory history = medicalHistoryRepository.findByIdAndPatientId(historyId, patientId)
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history not found with ID: " + historyId));
        if (!validateMedicalHistory(medicalHistoryDTO)) {
            throw new InvalidDataException("Invalid medical history data");
        }
        updateEntity(history, medicalHistoryDTO);
        history = medicalHistoryRepository.save(history);
        return toDTO(history);
    }

    @Override
    public List<MedicalHistoryDTO> getMedicalHistories(Long patientId, String startDate, String endDate)
            throws PatientNotFoundException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        if (startDate != null && endDate != null) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return medicalHistoryRepository.findByPatientIdAndVisitDateBetween(patientId, start, end)
                    .stream().map(this::toDTO).collect(Collectors.toList());
        }
        return medicalHistoryRepository.findByPatientId(patientId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDTO getMedicalHistoryById(Long patientId, Long historyId)
            throws PatientNotFoundException, MedicalHistoryNotFoundException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        MedicalHistory history = medicalHistoryRepository.findByIdAndPatientId(historyId, patientId)
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history not found with ID: " + historyId));
        return toDTO(history);
    }

    @Override
    public void deleteMedicalHistory(Long patientId, Long historyId)
            throws PatientNotFoundException, MedicalHistoryNotFoundException {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        MedicalHistory history = medicalHistoryRepository.findByIdAndPatientId(historyId, patientId)
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history not found with ID: " + historyId));
        medicalHistoryRepository.delete(history);
    }

    @Override
    public boolean validateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) throws InvalidDataException {
        if (medicalHistoryDTO.getVisitDate() == null) {
            return false;
        }
        try {
            LocalDate.parse(medicalHistoryDTO.getVisitDate());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private MedicalHistory toEntity(MedicalHistoryDTO dto) {
        return MedicalHistory.builder()
                .diagnosis(dto.getDiagnosis())
                .treatment(dto.getTreatment())
                .medication(dto.getMedication())
                .allergies(dto.getAllergies())
                .visitDate(LocalDate.parse(dto.getVisitDate()))
                .doctorId(dto.getDoctorId())
                .notes(dto.getNotes())
                .build();
    }

    private MedicalHistoryDTO toDTO(MedicalHistory history) {
        return MedicalHistoryDTO.builder()
                .id(history.getId())
                .patientId(history.getPatientId())
                .diagnosis(history.getDiagnosis())
                .treatment(history.getTreatment())
                .medication(history.getMedication())
                .allergies(history.getAllergies())
                .visitDate(history.getVisitDate().toString())
                .doctorId(history.getDoctorId())
                .notes(history.getNotes())
                .build();
    }

    private void updateEntity(MedicalHistory history, MedicalHistoryDTO dto) {
        history.setDiagnosis(dto.getDiagnosis());
        history.setTreatment(dto.getTreatment());
        history.setMedication(dto.getMedication());
        history.setAllergies(dto.getAllergies());
        history.setVisitDate(LocalDate.parse(dto.getVisitDate()));
        history.setDoctorId(dto.getDoctorId());
        history.setNotes(dto.getNotes());
    }
}
