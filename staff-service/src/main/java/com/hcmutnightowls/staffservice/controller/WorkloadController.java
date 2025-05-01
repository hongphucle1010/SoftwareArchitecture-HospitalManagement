package com.hcmutnightowls.staffservice.controller;

import com.hcmutnightowls.staffservice.dto.ResponseObject;
import com.hcmutnightowls.staffservice.dto.WorkloadRequest;
import com.hcmutnightowls.staffservice.model.Workload;
import com.hcmutnightowls.staffservice.service.WorkloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/staff/workloads")
@RequiredArgsConstructor
public class WorkloadController {

    private final WorkloadService workloadService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<Workload> recordWorkload(@RequestBody WorkloadRequest workloadRequest) {
        return ResponseObject.<Workload>builder()
                .status(HttpStatus.CREATED.value())
                .message("Workload recorded successfully")
                .data(workloadService.recordWorkload(workloadRequest))
                .build();
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Workload> updateWorkload(@PathVariable Long id, @RequestBody WorkloadRequest workloadRequest) {
        return ResponseObject.<Workload>builder()
                .status(HttpStatus.OK.value())
                .message("Workload updated successfully")
                .data(workloadService.updateWorkload(id, workloadRequest))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Workload> getWorkloadById(@PathVariable Long id) {
        return ResponseObject.<Workload>builder()
                .status(HttpStatus.OK.value())
                .message("Workload retrieved successfully")
                .data(workloadService.getWorkloadById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Workload>> getAllWorkloads() {
        return ResponseObject.<List<Workload>>builder()
                .status(HttpStatus.OK.value())
                .message("All workloads retrieved successfully")
                .data(workloadService.getAllWorkloads())
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/staff/{staffId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Workload>> getWorkloadsByStaffId(@PathVariable Long staffId) {
        return ResponseObject.<List<Workload>>builder()
                .status(HttpStatus.OK.value())
                .message("Workloads for staff retrieved successfully")
                .data(workloadService.getWorkloadsByStaffId(staffId))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/staff/{staffId}/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Workload>> getWorkloadsByStaffIdAndDateRange(
            @PathVariable Long staffId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseObject.<List<Workload>>builder()
                .status(HttpStatus.OK.value())
                .message("Workloads for staff in date range retrieved successfully")
                .data(workloadService.getWorkloadsByStaffIdAndDateRange(staffId, startDate, endDate))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Workload>> getWorkloadsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseObject.<List<Workload>>builder()
                .status(HttpStatus.OK.value())
                .message("Workloads in date range retrieved successfully")
                .data(workloadService.getWorkloadsByDateRange(startDate, endDate))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/date/{date}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Workload>> getWorkloadsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseObject.<List<Workload>>builder()
                .status(HttpStatus.OK.value())
                .message("Workloads for date retrieved successfully")
                .data(workloadService.getWorkloadsByDate(date))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<String> deleteWorkload(@PathVariable Long id) {
        workloadService.deleteWorkload(id);
        return ResponseObject.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Workload deleted successfully")
                .data("Workload with ID " + id + " deleted successfully")
                .build();
    }
}