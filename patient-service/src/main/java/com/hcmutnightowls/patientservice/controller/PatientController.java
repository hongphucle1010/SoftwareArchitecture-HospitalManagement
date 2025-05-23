package com.hcmutnightowls.patientservice.controller;


import com.hcmutnightowls.patientservice.configuration.JwtDecoderConfig;
import com.hcmutnightowls.patientservice.dto.patientDto.AuthenDTO;
import com.hcmutnightowls.patientservice.dto.patientDto.PatientDTO;
import com.hcmutnightowls.patientservice.dto.patientDto.RegisterDTO;
import com.hcmutnightowls.patientservice.dto.ResponseObject;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.service.interf.AuthServiceClient;
import com.hcmutnightowls.patientservice.service.interf.PatientQueryService;
import com.hcmutnightowls.patientservice.service.interf.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PatientQueryService patientQueryService;
    private final AuthServiceClient authServiceClient;
    private final JwtDecoderConfig jwtDecoderConfig;
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseObject<String> test() {
//        // Hello world
//        return ResponseObject.<String>builder()
//                .status(HttpStatus.OK.value())
//                .message("successfully")
//                .data("Hello world")
//                .build();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<PatientDTO> registerPatient(@Valid @RequestBody RegisterDTO registerDTO) {
        PatientDTO patientDTO = PatientDTO.builder()
                .fullName(registerDTO.getFullName())
                .email(registerDTO.getEmail())
                .phoneNumber(registerDTO.getPhoneNumber())
                .address(registerDTO.getAddress())
                .dateOfBirth(registerDTO.getDateOfBirth())
                .gender(registerDTO.getGender())
                .nationalId(registerDTO.getNationalId())
                .bloodType(registerDTO.getBloodType())
                .registrationDate(registerDTO.getRegistrationDate())
                .isActive(registerDTO.isActive()).build();

        PatientDTO result = patientService.registerPatient(patientDTO);

        AuthenDTO authenDTO = AuthenDTO.builder()
                .id(result.getId())
                .subject(registerDTO.getSubject())
                .password(registerDTO.getPassword()).build();

        authServiceClient.postPatient(authenDTO);

        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Patient registered successfully")
                .data(result)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<PatientDTO> updatePatient(@PathVariable Long id,
                                                    @Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO result = patientService.updatePatient(id, patientDTO);
        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient updated successfully")
                .data(result)
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<PatientDTO> partialUpdatePatient(@PathVariable Long id,
                                                           @RequestBody Map<String, Object> fields) {
        PatientDTO result = patientService.partialUpdatePatient(id, fields);
        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient partially updated successfully")
                .data(result)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Void> deactivatePatient(@PathVariable Long id) {
        patientService.deactivatePatient(id);
        return ResponseObject.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Patient deactivated successfully")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/reactivate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<PatientDTO> reactivatePatient(@PathVariable Long id) {
        PatientDTO result = patientService.reactivatePatient(id);
        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient reactivated successfully")
                .data(result)
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO result = patientQueryService.getPatientById(id);
        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient retrieved successfully")
                .data(result)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<PatientDTO>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {
        List<PatientDTO> result = patientQueryService.getAllPatients(page, size, search);
        return ResponseObject.<List<PatientDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Patients retrieved successfully")
                .data(result)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/national-id/{nationalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<PatientDTO> getPatientByNationalId(@PathVariable String nationalId) {
        PatientDTO result = patientQueryService.getPatientByNationalId(nationalId);
        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient retrieved successfully")
                .data(result)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<PatientDTO>> getActivePatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<PatientDTO> result = patientQueryService.getActivePatients(page, size);
        return ResponseObject.<List<PatientDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Active patients retrieved successfully")
                .data(result)
                .build();
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<PatientDTO> getMe(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String id = "-1";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Bỏ "Bearer "
            Jwt jwt = jwtDecoderConfig.jwtDecoder().decode(token);
            id = jwt.getClaimAsString("jti");
        }
        PatientDTO result = patientQueryService.getPatientById(Long.parseLong(id));
        return ResponseObject.<PatientDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient retrieved successfully")
                .data(result)
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

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject<Void> handleInvalidData(InvalidDataException ex) {
        return ResponseObject.<Void>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
    }
}