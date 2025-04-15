package com.hcmutnightowls.staffservice.service;

import com.hcmutnightowls.staffservice.dto.WorkloadRequest;
import com.hcmutnightowls.staffservice.model.Workload;

import java.time.LocalDate;
import java.util.List;

public interface WorkloadService {
    Workload recordWorkload(WorkloadRequest workloadRequest);
    Workload updateWorkload(Long id, WorkloadRequest workloadRequest);
    Workload getWorkloadById(Long id);
    List<Workload> getAllWorkloads();
    List<Workload> getWorkloadsByStaffId(Long staffId);
    List<Workload> getWorkloadsByStaffIdAndDateRange(Long staffId, LocalDate startDate, LocalDate endDate);
    List<Workload> getWorkloadsByDateRange(LocalDate startDate, LocalDate endDate);
    List<Workload> getWorkloadsByDate(LocalDate date);
    void deleteWorkload(Long id);
}