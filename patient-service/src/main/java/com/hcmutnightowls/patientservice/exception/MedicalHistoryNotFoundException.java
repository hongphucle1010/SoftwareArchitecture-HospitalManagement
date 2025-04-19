package com.hcmutnightowls.patientservice.exception;

public class MedicalHistoryNotFoundException extends RuntimeException {
    public MedicalHistoryNotFoundException(String message) {
        super(message);
    }
}
