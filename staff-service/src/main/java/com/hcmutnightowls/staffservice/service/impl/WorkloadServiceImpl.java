package com.hcmutnightowls.staffservice.service.impl;

import com.hcmutnightowls.staffservice.dto.WorkloadRequest;
import com.hcmutnightowls.staffservice.model.Workload;
import com.hcmutnightowls.staffservice.repository.WorkloadRepository;
import com.hcmutnightowls.staffservice.service.StaffService;
import com.hcmutnightowls.staffservice.service.WorkloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;
    private final StaffService staffService;

    @Override
    public Workload recordWorkload(WorkloadRequest workloadRequest) {
        // Verify that the staff exists
        staffService.getStaffById(workloadRequest.getStaffId());
        
        Workload workload = Workload.builder()
                .staffId(workloadRequest.getStaffId())
                .date(workloadRequest.getDate())
                .patientCount(workloadRequest.getPatientCount())
                .appointmentCount(workloadRequest.getAppointmentCount())
                .procedureCount(workloadRequest.getProcedureCount())
                .surgeryCount(workloadRequest.getSurgeryCount())
                .consultationCount(workloadRequest.getConsultationCount())
                .hoursWorked(workloadRequest.getHoursWorked())
                .notes(workloadRequest.getNotes())
                .build();
        
        return workloadRepository.save(workload);
    }

    @Override
    public Workload updateWorkload(Long id, WorkloadRequest workloadRequest) {
        Workload existingWorkload = getWorkloadById(id);
        
        // Verify that the staff exists if staffId is being updated
        if (!existingWorkload.getStaffId().equals(workloadRequest.getStaffId())) {
            staffService.getStaffById(workloadRequest.getStaffId());
        }
        
        existingWorkload.setStaffId(workloadRequest.getStaffId());
        existingWorkload.setDate(workloadRequest.getDate());
        existingWorkload.setPatientCount(workloadRequest.getPatientCount());
        existingWorkload.setAppointmentCount(workloadRequest.getAppointmentCount());
        existingWorkload.setProcedureCount(workloadRequest.getProcedureCount());
        existingWorkload.setSurgeryCount(workloadRequest.getSurgeryCount());
        existingWorkload.setConsultationCount(workloadRequest.getConsultationCount());
        existingWorkload.setHoursWorked(workloadRequest.getHoursWorked());
        existingWorkload.setNotes(workloadRequest.getNotes());
        
        return workloadRepository.save(existingWorkload);
    }

    @Override
    public Workload getWorkloadById(Long id) {
        return workloadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workload not found with id: " + id));
    }

    @Override
    public List<Workload> getAllWorkloads() {
        return workloadRepository.findAll();
    }

    @Override
    public List<Workload> getWorkloadsByStaffId(Long staffId) {
        return workloadRepository.findByStaffId(staffId);
    }

    @Override
    public List<Workload> getWorkloadsByStaffIdAndDateRange(Long staffId, LocalDate startDate, LocalDate endDate) {
        return workloadRepository.findByStaffIdAndDateBetween(staffId, startDate, endDate);
    }

    @Override
    public List<Workload> getWorkloadsByDateRange(LocalDate startDate, LocalDate endDate) {
        return workloadRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<Workload> getWorkloadsByDate(LocalDate date) {
        return workloadRepository.findByDate(date);
    }

    @Override
    public void deleteWorkload(Long id) {
        workloadRepository.deleteById(id);
    }
}