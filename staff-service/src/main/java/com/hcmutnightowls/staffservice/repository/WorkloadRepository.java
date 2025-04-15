package com.hcmutnightowls.staffservice.repository;

import com.hcmutnightowls.staffservice.model.Workload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkloadRepository extends JpaRepository<Workload, Long> {
    List<Workload> findByStaffId(Long staffId);
    List<Workload> findByStaffIdAndDateBetween(Long staffId, LocalDate startDate, LocalDate endDate);
    List<Workload> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Workload> findByDate(LocalDate date);
}