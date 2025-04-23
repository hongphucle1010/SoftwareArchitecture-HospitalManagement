package com.hcmutnightowls.patientservice.repository;

import com.hcmutnightowls.patientservice.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByPatientId(Long patientId);
    Optional<MedicalHistory> findByIdAndPatientId(Long id, Long patientId);
    List<MedicalHistory> findByPatientIdAndVisitDateBetween(Long patientId, LocalDate startDate, LocalDate endDate);
}
