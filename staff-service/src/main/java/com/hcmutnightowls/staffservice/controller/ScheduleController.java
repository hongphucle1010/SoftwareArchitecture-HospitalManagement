package com.hcmutnightowls.staffservice.controller;

import com.hcmutnightowls.staffservice.dto.ResponseObject;
import com.hcmutnightowls.staffservice.dto.ScheduleRequest;
import com.hcmutnightowls.staffservice.model.Schedule;
import com.hcmutnightowls.staffservice.model.ShiftType;
import com.hcmutnightowls.staffservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/staff/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<Schedule> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return ResponseObject.<Schedule>builder()
                .status(HttpStatus.CREATED.value())
                .message("Schedule created successfully")
                .data(scheduleService.createSchedule(scheduleRequest))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseObject.<Schedule>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule updated successfully")
                .data(scheduleService.updateSchedule(id, scheduleRequest))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Schedule> getScheduleById(@PathVariable Long id) {
        return ResponseObject.<Schedule>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule retrieved successfully")
                .data(scheduleService.getScheduleById(id))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getAllSchedules() {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("All schedules retrieved successfully")
                .data(scheduleService.getAllSchedules())
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/staff/{staffId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByStaffId(@PathVariable Long staffId) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules for staff retrieved successfully")
                .data(scheduleService.getSchedulesByStaffId(staffId))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/staff/{staffId}/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByStaffIdAndDateRange(
            @PathVariable Long staffId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules for staff in date range retrieved successfully")
                .data(scheduleService.getSchedulesByStaffIdAndDateRange(staffId, start, end))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByDepartment(@PathVariable String department) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules by department retrieved successfully")
                .data(scheduleService.getSchedulesByDepartment(department))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/shift-type/{shiftType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByShiftType(@PathVariable ShiftType shiftType) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules by shift type retrieved successfully")
                .data(scheduleService.getSchedulesByShiftType(shiftType))
                .build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules in date range retrieved successfully")
                .data(scheduleService.getSchedulesByDateRange(start, end))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getActiveSchedules() {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Active schedules retrieved successfully")
                .data(scheduleService.getActiveSchedules())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseObject.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule deleted successfully")
                .data("Schedule with ID " + id + " deleted successfully")
                .build();
    }
}