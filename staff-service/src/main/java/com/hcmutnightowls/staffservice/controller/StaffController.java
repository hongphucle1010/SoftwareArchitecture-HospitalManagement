package com.hcmutnightowls.staffservice.controller;

import com.hcmutnightowls.staffservice.dto.ResponseObject;
import com.hcmutnightowls.staffservice.dto.StaffRequest;
import com.hcmutnightowls.staffservice.model.Staff;
import com.hcmutnightowls.staffservice.model.StaffType;
import com.hcmutnightowls.staffservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    
    private final StaffService staffService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<Staff> createStaff(@RequestBody StaffRequest staffRequest) {
        return ResponseObject.<Staff>builder()
                .status(HttpStatus.CREATED.value())
                .message("Staff created successfully")
                .data(staffService.createStaff(staffRequest))
                .build();
    }
    @PreAuthorize("hasRole('STAFF')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Staff> updateStaff(@PathVariable Long id, @RequestBody StaffRequest staffRequest) {
        return ResponseObject.<Staff>builder()
                .status(HttpStatus.OK.value())
                .message("Staff updated successfully")
                .data(staffService.updateStaff(id, staffRequest))
                .build();
    }
    @PreAuthorize("hasRole('STAFF')")
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
    
    @GetMapping("/type/{staffType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getStaffByType(@PathVariable StaffType staffType) {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("Staff by type retrieved successfully")
                .data(staffService.getStaffByType(staffType))
                .build();
    }
    
    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Staff>> getStaffByDepartment(@PathVariable String department) {
        return ResponseObject.<List<Staff>>builder()
                .status(HttpStatus.OK.value())
                .message("Staff by department retrieved successfully")
                .data(staffService.getStaffByDepartment(department))
                .build();
    }
    
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
}
