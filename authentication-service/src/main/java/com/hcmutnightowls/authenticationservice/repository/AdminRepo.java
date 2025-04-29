package com.hcmutnightowls.authenticationservice.repository;

import com.hcmutnightowls.authenticationservice.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findBySubject(String subject);
}
