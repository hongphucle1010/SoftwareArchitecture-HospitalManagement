package com.hcmutnightowls.staffservice.controller;

import com.hcmutnightowls.staffservice.config.JwtDecoderConfig;
import com.hcmutnightowls.staffservice.dto.AuthenClientRequest;
import com.hcmutnightowls.staffservice.dto.ResponseObject;
import com.hcmutnightowls.staffservice.dto.StaffRequest;
import com.hcmutnightowls.staffservice.model.Staff;
import com.hcmutnightowls.staffservice.model.StaffType;
import com.hcmutnightowls.staffservice.service.AuthServiceClient;
import com.hcmutnightowls.staffservice.service.StaffService;
import com.nimbusds.jwt.JWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    
    private final StaffService staffService;
    private final AuthServiceClient authServiceClient;
    private final JwtDecoderConfig jwtDecoderConfig;
    private final RestClient.Builder builder;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<Staff> createStaff(@RequestBody StaffRequest staffRequest) {
        Staff newStaff = staffService.createStaff(staffRequest);

        // Call the authentication service to create the staff account
        AuthenClientRequest authenClientRequest = AuthenClientRequest.builder()
                .id(newStaff.getId())
                .subject(staffRequest.getSubject())
                .password(staffRequest.getPassword())
                .build();

        authServiceClient.postStaff(authenClientRequest);

        return ResponseObject.<Staff>builder()
                .status(HttpStatus.CREATED.value())
                .message("Staff created successfully")
                .data(newStaff)
                .build();
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Staff> updateStaff(@PathVariable Long id, @RequestBody StaffRequest staffRequest) {
        return ResponseObject.<Staff>builder()
                .status(HttpStatus.OK.value())
                .message("Staff updated successfully")
                .data(staffService.updateStaff(id, staffRequest))
                .build();
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Staff> getStaffById(@PathVariable Long id) {
        return ResponseObject.<Staff>builder()
                .status(HttpStatus.OK.value())
                .message("Staff retrieved successfully")
                .data(staffService.getStaffById(id))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getAllStaff() {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("All staff retrieved successfully")
                .data(staffService.getAllStaff())
                .build();
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/type/{staffType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getStaffByType(@PathVariable StaffType staffType) {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("Staff by type retrieved successfully")
                .data(staffService.getStaffByType(staffType))
                .build();
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getStaffByDepartment(@PathVariable String department) {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("Staff by department retrieved successfully")
                .data(staffService.getStaffByDepartment(department))
                .build();
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/specialization/{specialization}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getStaffBySpecialization(@PathVariable String specialization) {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("Staff by specialization retrieved successfully")
                .data(staffService.getStaffBySpecialization(specialization))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getActiveStaff() {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("Active staff retrieved successfully")
                .data(staffService.getActiveStaff())
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<String> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseObject.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Staff deleted successfully")
                .data("Staff with ID " + id + " deleted successfully")
                .build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseObject<Staff> getCurrentUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Jwt jwt = jwtDecoderConfig.jwtDecoder().decode(token);
            String id = jwt.getClaimAsString("jti");

            Staff currentStaff = staffService.getStaffById(Long.parseLong(id));

            return ResponseObject.<Staff>builder()
                    .status(HttpStatus.OK.value())
                    .message("Get staff successfully")
                    .data(currentStaff)
                    .build();
        }
        return null;
    }
}
