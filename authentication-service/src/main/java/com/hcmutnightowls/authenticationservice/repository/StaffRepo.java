package com.hcmutnightowls.authenticationservice.repository;

import com.hcmutnightowls.authenticationservice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer> {
    Optional<Staff> findBySubject(String subject);
}
