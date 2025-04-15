package com.hcmutnightowls.staffservice.repository;

import com.hcmutnightowls.staffservice.model.Staff;
import com.hcmutnightowls.staffservice.model.StaffType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByStaffType(StaffType staffType);
    List<Staff> findByDepartment(String department);
    Optional<Staff> findByEmail(String email);
    
    @Query("SELECT s FROM Staff s JOIN s.specializations spec WHERE spec = :specialization")
    List<Staff> findBySpecializationsContaining(String specialization);
    
    List<Staff> findByIsActive(boolean isActive);
}