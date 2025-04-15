package com.hcmutnightowls.staffservice.service.impl;

import com.hcmutnightowls.staffservice.dto.StaffRequest;
import com.hcmutnightowls.staffservice.model.Staff;
import com.hcmutnightowls.staffservice.model.StaffType;
import com.hcmutnightowls.staffservice.repository.StaffRepository;
import com.hcmutnightowls.staffservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public Staff createStaff(StaffRequest staffRequest) {
        Staff staff = Staff.builder()
                .fullName(staffRequest.getFullName())
                .email(staffRequest.getEmail())
                .phoneNumber(staffRequest.getPhoneNumber())
                .address(staffRequest.getAddress())
                .dateOfBirth(staffRequest.getDateOfBirth())
                .gender(staffRequest.getGender())
                .staffType(staffRequest.getStaffType())
                .department(staffRequest.getDepartment())
                .position(staffRequest.getPosition())
                .qualifications(staffRequest.getQualifications())
                .specializations(staffRequest.getSpecializations())
                .joiningDate(staffRequest.getJoiningDate())
                .isActive(staffRequest.isActive())
                .build();
        
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Long id, StaffRequest staffRequest) {
        Staff existingStaff = getStaffById(id);
        
        existingStaff.setFullName(staffRequest.getFullName());
        existingStaff.setEmail(staffRequest.getEmail());
        existingStaff.setPhoneNumber(staffRequest.getPhoneNumber());
        existingStaff.setAddress(staffRequest.getAddress());
        existingStaff.setDateOfBirth(staffRequest.getDateOfBirth());
        existingStaff.setGender(staffRequest.getGender());
        existingStaff.setStaffType(staffRequest.getStaffType());
        existingStaff.setDepartment(staffRequest.getDepartment());
        existingStaff.setPosition(staffRequest.getPosition());
        existingStaff.setQualifications(staffRequest.getQualifications());
        existingStaff.setSpecializations(staffRequest.getSpecializations());
        existingStaff.setJoiningDate(staffRequest.getJoiningDate());
        existingStaff.setActive(staffRequest.isActive());
        
        return staffRepository.save(existingStaff);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> getStaffByType(StaffType type) {
        return staffRepository.findByStaffType(type);
    }

    @Override
    public List<Staff> getStaffByDepartment(String department) {
        return staffRepository.findByDepartment(department);
    }

    @Override
    public List<Staff> getStaffBySpecialization(String specialization) {
        return staffRepository.findBySpecializationsContaining(specialization);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> getActiveStaff() {
        return staffRepository.findByIsActive(true);
    }
}