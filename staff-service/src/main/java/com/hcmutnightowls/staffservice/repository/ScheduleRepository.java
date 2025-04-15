package com.hcmutnightowls.staffservice.repository;

import com.hcmutnightowls.staffservice.model.Schedule;
import com.hcmutnightowls.staffservice.model.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStaffId(Long staffId);
    List<Schedule> findByStaffIdAndStartTimeBetween(Long staffId, LocalDateTime start, LocalDateTime end);
    List<Schedule> findByDepartment(String department);
    List<Schedule> findByShiftType(ShiftType shiftType);
    List<Schedule> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Schedule> findByIsActive(boolean isActive);
}