package com.hcmutnightowls.staffservice.service;

import com.hcmutnightowls.staffservice.dto.StaffRequest;
import com.hcmutnightowls.staffservice.model.Staff;
import com.hcmutnightowls.staffservice.model.StaffType;

import java.util.List;

public interface StaffService {
    Staff createStaff(StaffRequest staffRequest);
    Staff updateStaff(Long id, StaffRequest staffRequest);
    Staff getStaffById(Long id);
    List<Staff> getAllStaff();
    List<Staff> getStaffByType(StaffType type);
    List<Staff> getStaffByDepartment(String department);
    List<Staff> getStaffBySpecialization(String specialization);
    void deleteStaff(Long id);
    List<Staff> getActiveStaff();
}