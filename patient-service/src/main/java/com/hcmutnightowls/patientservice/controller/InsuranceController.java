package com.hcmutnightowls.patientservice.controller;

import com.hcmutnightowls.patientservice.dto.InsuranceDTO;
import com.hcmutnightowls.patientservice.dto.ResponseObject;
import com.hcmutnightowls.patientservice.exception.InsuranceNotFoundException;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.service.interf.InsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient/{patientId}/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<InsuranceDTO> addInsurance(
            @PathVariable Long patientId, @Valid @RequestBody InsuranceDTO insuranceDTO) {
        InsuranceDTO result = insuranceService.addInsurance(patientId, insuranceDTO);
        return ResponseObject.<InsuranceDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Insurance added successfully")
                .data(result)
                .build();
    }

    @PutMapping("/{insuranceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<InsuranceDTO> updateInsurance(
            @PathVariable Long patientId, @PathVariable Long insuranceId,
            @Valid @RequestBody InsuranceDTO insuranceDTO) {
        InsuranceDTO result = insuranceService.updateInsurance(patientId, insuranceId, insuranceDTO);
        return ResponseObject.<InsuranceDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Insurance updated successfully")
                .data(result)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<InsuranceDTO>> getInsuranceDetails(@PathVariable Long patientId) {
        List<InsuranceDTO> result = insuranceService.getInsuranceDetails(patientId);
        return ResponseObject.<List<InsuranceDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Insurance details retrieved successfully")
                .data(result)
                .build();
    }

    @DeleteMapping("/{insuranceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseObject<Void> deleteInsurance(
            @PathVariable Long patientId, @PathVariable Long insuranceId) {
        insuranceService.deleteInsurance(patientId, insuranceId);
        return ResponseObject.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Insurance deleted successfully")
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

    @ExceptionHandler(InsuranceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject<Void> handleInsuranceNotFound(InsuranceNotFoundException ex) {
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
