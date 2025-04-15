-- Staff Service Database Schema
CREATE TABLE IF NOT EXISTS staff (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    date_of_birth DATE,
    gender VARCHAR(10),
    staff_type VARCHAR(20) NOT NULL,
    department VARCHAR(100),
    position VARCHAR(100),
    joining_date DATE,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS staff_qualifications (
    staff_id BIGINT NOT NULL,
    qualification VARCHAR(255) NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS staff_specializations (
    staff_id BIGINT NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS schedules (
    id SERIAL PRIMARY KEY,
    staff_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    shift_type VARCHAR(20) NOT NULL,
    department VARCHAR(100),
    notes TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS workloads (
    id SERIAL PRIMARY KEY,
    staff_id BIGINT NOT NULL,
    date DATE NOT NULL,
    patient_count INTEGER DEFAULT 0,
    appointment_count INTEGER DEFAULT 0,
    procedure_count INTEGER DEFAULT 0,
    surgery_count INTEGER DEFAULT 0,
    consultation_count INTEGER DEFAULT 0,
    hours_worked NUMERIC(5,2) DEFAULT 0.0,
    notes TEXT,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_staff_email ON staff(email);
CREATE INDEX IF NOT EXISTS idx_staff_department ON staff(department);
CREATE INDEX IF NOT EXISTS idx_staff_staff_type ON staff(staff_type);
CREATE INDEX IF NOT EXISTS idx_schedules_staff_id ON schedules(staff_id);
CREATE INDEX IF NOT EXISTS idx_schedules_start_time ON schedules(start_time);
CREATE INDEX IF NOT EXISTS idx_workloads_staff_id ON workloads(staff_id);
CREATE INDEX IF NOT EXISTS idx_workloads_date ON workloads(date);