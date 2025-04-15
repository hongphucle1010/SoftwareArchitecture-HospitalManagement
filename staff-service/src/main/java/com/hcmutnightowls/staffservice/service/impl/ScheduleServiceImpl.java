package com.hcmutnightowls.staffservice.service.impl;

import com.hcmutnightowls.staffservice.dto.ScheduleRequest;
import com.hcmutnightowls.staffservice.model.Schedule;
import com.hcmutnightowls.staffservice.model.ShiftType;
import com.hcmutnightowls.staffservice.repository.ScheduleRepository;
import com.hcmutnightowls.staffservice.service.ScheduleService;
import com.hcmutnightowls.staffservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final StaffService staffService;

    @Override
    public Schedule createSchedule(ScheduleRequest scheduleRequest) {
        // Verify that the staff exists
        staffService.getStaffById(scheduleRequest.getStaffId());
        
        Schedule schedule = Schedule.builder()
                .staffId(scheduleRequest.getStaffId())
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getEndTime())
                .shiftType(scheduleRequest.getShiftType())
                .department(scheduleRequest.getDepartment())
                .notes(scheduleRequest.getNotes())
                .isActive(scheduleRequest.isActive())
                .build();
        
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Long id, ScheduleRequest scheduleRequest) {
        Schedule existingSchedule = getScheduleById(id);
        
        // Verify that the staff exists if staffId is being updated
        if (!existingSchedule.getStaffId().equals(scheduleRequest.getStaffId())) {
            staffService.getStaffById(scheduleRequest.getStaffId());
        }
        
        existingSchedule.setStaffId(scheduleRequest.getStaffId());
        existingSchedule.setStartTime(scheduleRequest.getStartTime());
        existingSchedule.setEndTime(scheduleRequest.getEndTime());
        existingSchedule.setShiftType(scheduleRequest.getShiftType());
        existingSchedule.setDepartment(scheduleRequest.getDepartment());
        existingSchedule.setNotes(scheduleRequest.getNotes());
        existingSchedule.setActive(scheduleRequest.isActive());
        
        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getSchedulesByStaffId(Long staffId) {
        return scheduleRepository.findByStaffId(staffId);
    }

    @Override
    public List<Schedule> getSchedulesByStaffIdAndDateRange(Long staffId, LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findByStaffIdAndStartTimeBetween(staffId, start, end);
    }

    @Override
    public List<Schedule> getSchedulesByDepartment(String department) {
        return scheduleRepository.findByDepartment(department);
    }

    @Override
    public List<Schedule> getSchedulesByShiftType(ShiftType shiftType) {
        return scheduleRepository.findByShiftType(shiftType);
    }

    @Override
    public List<Schedule> getSchedulesByDateRange(LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findByStartTimeBetween(start, end);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<Schedule> getActiveSchedules() {
        return scheduleRepository.findByIsActive(true);
    }
}