-- Patient Management Database Schema
CREATE TABLE IF NOT EXISTS patients (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    date_of_birth DATE,
    gender VARCHAR(10),
    national_id VARCHAR(50) UNIQUE,
    blood_type VARCHAR(10),
    registration_date DATE NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS medical_histories (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    diagnosis TEXT,
    treatment TEXT,
    medication TEXT,
    allergies TEXT,
    visit_date DATE NOT NULL,
    doctor_id BIGINT,
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
);

CREATE TABLE IF NOT EXISTS patient_insurance (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    insurance_provider VARCHAR(255),
    policy_number VARCHAR(50),
    valid_from DATE,
    valid_until DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE
);

-- Indexes for performance optimization
CREATE INDEX IF NOT EXISTS idx_patients_email ON patients(email);
CREATE INDEX IF NOT EXISTS idx_patients_national_id ON patients(national_id);
CREATE INDEX IF NOT EXISTS idx_medical_histories_patient_id ON medical_histories(patient_id);
CREATE INDEX IF NOT EXISTS idx_medical_histories_visit_date ON medical_histories(visit_date);
CREATE INDEX IF NOT EXISTS idx_patient_insurance_patient_id ON patient_insurance(patient_id);