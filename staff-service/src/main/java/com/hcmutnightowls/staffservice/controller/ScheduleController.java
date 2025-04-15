package com.hcmutnightowls.staffservice.controller;

import com.hcmutnightowls.common.dto.ResponseObject;
import com.hcmutnightowls.staffservice.dto.ScheduleRequest;
import com.hcmutnightowls.staffservice.model.Schedule;
import com.hcmutnightowls.staffservice.model.ShiftType;
import com.hcmutnightowls.staffservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/staff/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject<Schedule> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return ResponseObject.<Schedule>builder()
                .status(HttpStatus.CREATED.value())
                .message("Schedule created successfully")
                .data(scheduleService.createSchedule(scheduleRequest))
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseObject.<Schedule>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule updated successfully")
                .data(scheduleService.updateSchedule(id, scheduleRequest))
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<Schedule> getScheduleById(@PathVariable Long id) {
        return ResponseObject.<Schedule>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule retrieved successfully")
                .data(scheduleService.getScheduleById(id))
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getAllSchedules() {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("All schedules retrieved successfully")
                .data(scheduleService.getAllSchedules())
                .build();
    }

    @GetMapping("/staff/{staffId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByStaffId(@PathVariable Long staffId) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules for staff retrieved successfully")
                .data(scheduleService.getSchedulesByStaffId(staffId))
                .build();
    }

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

    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByDepartment(@PathVariable String department) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules by department retrieved successfully")
                .data(scheduleService.getSchedulesByDepartment(department))
                .build();
    }

    @GetMapping("/shift-type/{shiftType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getSchedulesByShiftType(@PathVariable ShiftType shiftType) {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules by shift type retrieved successfully")
                .data(scheduleService.getSchedulesByShiftType(shiftType))
                .build();
    }

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

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject<List<Schedule>> getActiveSchedules() {
        return ResponseObject.<List<Schedule>>builder()
                .status(HttpStatus.OK.value())
                .message("Active schedules retrieved successfully")
                .data(scheduleService.getActiveSchedules())
                .build();
    }

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