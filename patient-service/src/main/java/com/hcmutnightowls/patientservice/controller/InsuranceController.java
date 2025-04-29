package com.hcmutnightowls.patientservice.controller;

import com.hcmutnightowls.patientservice.dto.ResponseObject;
import com.hcmutnightowls.patientservice.dto.insuranceDto.CreateInsuranceDTO;
import com.hcmutnightowls.patientservice.dto.insuranceDto.InsuranceDTO;
import com.hcmutnightowls.patientservice.dto.insuranceDto.UpdateInsuranceDTO;
import com.hcmutnightowls.patientservice.exception.InsuranceNotFoundException;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;
import com.hcmutnightowls.patientservice.service.interf.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient/insurance")
@RequiredArgsConstructor
@Tag(name = "Insurance Controller", description = "API để quản lý thông tin bảo hiểm của bệnh nhân")
public class InsuranceController {
    private final InsuranceService insuranceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Thêm thông tin bảo hiểm mới", description = "Thêm thông tin bảo hiểm mới cho bệnh nhân")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Thêm bảo hiểm thành công", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "404", description = "Không tìm thấy bệnh nhân", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseObject<InsuranceDTO> addInsurance(
            @Valid @RequestBody CreateInsuranceDTO insuranceDTO) {
        InsuranceDTO result = insuranceService.addInsurance(insuranceDTO);
        return ResponseObject.<InsuranceDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Insurance added successfully")
                .data(result)
                .build();
    }

    @PutMapping("/{insuranceId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cập nhật thông tin bảo hiểm", description = "Cập nhật thông tin bảo hiểm hiện có của bệnh nhân")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cập nhật bảo hiểm thành công", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "404", description = "Không tìm thấy bệnh nhân hoặc bảo hiểm", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseObject<InsuranceDTO> updateInsurance(
            @Parameter(description = "ID của bảo hiểm cần cập nhật") @PathVariable Long insuranceId, 
            @Valid @RequestBody UpdateInsuranceDTO updateInsuranceDTO) {
                
        InsuranceDTO result = insuranceService.updateInsurance(insuranceId, updateInsuranceDTO);
        return ResponseObject.<InsuranceDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Insurance updated successfully")
                .data(result)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy danh sách bảo hiểm", description = "Lấy tất cả thông tin bảo hiểm của một bệnh nhân")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lấy danh sách bảo hiểm thành công", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "404", description = "Không tìm thấy bệnh nhân", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseObject<List<InsuranceDTO>> getInsuranceDetails(
            @Parameter(description = "ID của bệnh nhân") @PathVariable Long patientId) {
        List<InsuranceDTO> result = insuranceService.getInsuranceDetails(patientId);
        return ResponseObject.<List<InsuranceDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Insurance details retrieved successfully")
                .data(result)
                .build();
    }

    @DeleteMapping("/{insuranceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Xóa thông tin bảo hiểm", description = "Xóa thông tin bảo hiểm của bệnh nhân")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Xóa bảo hiểm thành công", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "404", description = "Không tìm thấy bệnh nhân hoặc bảo hiểm", 
                     content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseObject<Void> deleteInsurance(
            @Parameter(description = "ID của bệnh nhân") @PathVariable Long patientId, 
            @Parameter(description = "ID của bảo hiểm cần xóa") @PathVariable Long insuranceId) {
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
