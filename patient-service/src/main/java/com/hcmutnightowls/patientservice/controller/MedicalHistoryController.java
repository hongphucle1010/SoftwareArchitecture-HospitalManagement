package com.hcmutnightowls.patientservice.controller;


import com.hcmutnightowls.patientservice.dto.ResponseObject;
import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.CreateMedicalHistoryDTO;
import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.MedicalHistoryResponseDTO;
import com.hcmutnightowls.patientservice.dto.medicalHistoryDto.UpdateMedicalHistoryDTO;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.MedicalHistoryNotFoundException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.service.interf.MedicalHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient/{patientId}/medical-histories")
@RequiredArgsConstructor
@Validated
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<MedicalHistoryResponseDTO> addMedicalHistory(
            @PathVariable Long patientId, @Valid @RequestBody CreateMedicalHistoryDTO createMedicalHistoryDTO) {
        MedicalHistoryResponseDTO result = medicalHistoryService.addMedicalHistory(patientId, createMedicalHistoryDTO);
        return ResponseObject.<MedicalHistoryResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Medical history added successfully")
                .data(result)
                .build();
    }

    @PutMapping("/{historyId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<MedicalHistoryResponseDTO> updateMedicalHistory(
            @PathVariable Long patientId, @PathVariable Long historyId,
            @Valid @RequestBody UpdateMedicalHistoryDTO updateMedicalHistoryDTO) {
        MedicalHistoryResponseDTO result = medicalHistoryService.updateMedicalHistory(patientId, historyId, updateMedicalHistoryDTO);
        return ResponseObject.<MedicalHistoryResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Medical history updated successfully")
                .data(result)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<MedicalHistoryResponseDTO>> getMedicalHistories(
            @PathVariable Long patientId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        // Validate date format if parameters are provided
        if (startDate != null && !startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDataException("Start date must be in the format yyyy-MM-dd");
        }
        if (endDate != null && !endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDataException("End date must be in the format yyyy-MM-dd");
        }
        
        List<MedicalHistoryResponseDTO> result = medicalHistoryService.getMedicalHistories(patientId, startDate, endDate);
        return ResponseObject.<List<MedicalHistoryResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Medical histories retrieved successfully")
                .data(result)
                .build();
    }

    @GetMapping("/{historyId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<MedicalHistoryResponseDTO> getMedicalHistoryById(
            @PathVariable Long patientId, @PathVariable Long historyId) {
        MedicalHistoryResponseDTO result = medicalHistoryService.getMedicalHistoryById(patientId, historyId);
        return ResponseObject.<MedicalHistoryResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Medical history retrieved successfully")
                .data(result)
                .build();
    }

    @DeleteMapping("/{historyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseObject<Void> deleteMedicalHistory(
            @PathVariable Long patientId, @PathVariable Long historyId) {
        medicalHistoryService.deleteMedicalHistory(patientId, historyId);
        return ResponseObject.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Medical history deleted successfully")
                .build();
    }

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject<Void> handlePatientNotFound(PatientNotFoundException ex) {
        return ResponseObject.<Void>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MedicalHistoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject<Void> handleMedicalHistoryNotFound(MedicalHistoryNotFoundException ex) {
        return ResponseObject.<Void>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject<Void> handleInvalidData(InvalidDataException ex) {
        return ResponseObject.<Void>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
    }
}
