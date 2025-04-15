package com.hcmutnightowls.staffservice.service;

import com.hcmutnightowls.staffservice.dto.ScheduleRequest;
import com.hcmutnightowls.staffservice.model.Schedule;
import com.hcmutnightowls.staffservice.model.ShiftType;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequest scheduleRequest);
    Schedule updateSchedule(Long id, ScheduleRequest scheduleRequest);
    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    List<Schedule> getSchedulesByStaffId(Long staffId);
    List<Schedule> getSchedulesByStaffIdAndDateRange(Long staffId, LocalDateTime start, LocalDateTime end);
    List<Schedule> getSchedulesByDepartment(String department);
    List<Schedule> getSchedulesByShiftType(ShiftType shiftType);
    List<Schedule> getSchedulesByDateRange(LocalDateTime start, LocalDateTime end);
    void deleteSchedule(Long id);
    List<Schedule> getActiveSchedules();
}