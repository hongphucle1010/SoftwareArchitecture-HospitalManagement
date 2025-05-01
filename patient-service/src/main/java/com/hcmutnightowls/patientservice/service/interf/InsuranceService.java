package com.hcmutnightowls.patientservice.service.interf;

import com.hcmutnightowls.patientservice.dto.insuranceDto.CreateInsuranceDTO;
import com.hcmutnightowls.patientservice.dto.insuranceDto.InsuranceDTO;
import com.hcmutnightowls.patientservice.dto.insuranceDto.UpdateInsuranceDTO;
import com.hcmutnightowls.patientservice.exception.InsuranceNotFoundException;
import com.hcmutnightowls.patientservice.exception.InvalidDataException;
import com.hcmutnightowls.patientservice.exception.PatientNotFoundException;

import java.util.List;

public interface InsuranceService {
    InsuranceDTO addInsurance(CreateInsuranceDTO createInsuranceDTO)
            throws PatientNotFoundException, InvalidDataException;
    InsuranceDTO updateInsurance(Long id, UpdateInsuranceDTO updateInsuranceDTO)
            throws PatientNotFoundException, InsuranceNotFoundException, InvalidDataException;
    List<InsuranceDTO> getInsuranceDetails(Long patientId) throws PatientNotFoundException;
    void deleteInsurance(Long patientId, Long insuranceId)
            throws PatientNotFoundException, InsuranceNotFoundException;
    boolean validateInsurance(InsuranceDTO insuranceDTO) throws InvalidDataException;
}
