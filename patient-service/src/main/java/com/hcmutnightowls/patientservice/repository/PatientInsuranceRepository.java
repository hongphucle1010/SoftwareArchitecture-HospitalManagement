package com.hcmutnightowls.patientservice.repository;

import com.hcmutnightowls.patientservice.model.PatientInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientInsuranceRepository extends JpaRepository<PatientInsurance, Long> {
    List<PatientInsurance> findByPatientId(Long patientId);
    Optional<PatientInsurance> findByIdAndPatientId(Long id, Long patientId);
}
