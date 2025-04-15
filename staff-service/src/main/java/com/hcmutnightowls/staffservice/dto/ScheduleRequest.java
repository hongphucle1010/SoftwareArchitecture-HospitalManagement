package com.hcmutnightowls.staffservice.dto;

import com.hcmutnightowls.staffservice.model.ShiftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private Long staffId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ShiftType shiftType;
    private String department;
    private String notes;
    private boolean isActive;
}