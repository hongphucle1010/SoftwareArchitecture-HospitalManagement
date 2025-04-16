-- Staff Service Sample Data

-- Insert sample staff members
INSERT INTO staff (full_name, email, phone_number, address, date_of_birth, gender, staff_type, department, position, joining_date, is_active)
VALUES
    ('John Smith', 'john.smith@hospital.com', '123-456-7890', '123 Main St, City', '1980-05-15', 'Male', 'DOCTOR', 'Cardiology', 'Senior Cardiologist', '2015-03-10', true),
    ('Sarah Johnson', 'sarah.johnson@hospital.com', '234-567-8901', '456 Oak Ave, City', '1985-08-22', 'Female', 'DOCTOR', 'Neurology', 'Neurologist', '2017-06-20', true),
    ('Michael Brown', 'michael.brown@hospital.com', '345-678-9012', '789 Pine Rd, City', '1975-11-30', 'Male', 'DOCTOR', 'Orthopedics', 'Orthopedic Surgeon', '2010-01-15', true),
    ('Emily Davis', 'emily.davis@hospital.com', '456-789-0123', '101 Elm St, City', '1990-04-05', 'Female', 'NURSE', 'Emergency', 'Head Nurse', '2018-09-12', true),
    ('David Wilson', 'david.wilson@hospital.com', '567-890-1234', '202 Maple Dr, City', '1982-07-19', 'Male', 'NURSE', 'Cardiology', 'Registered Nurse', '2019-02-28', true),
    ('Jennifer Lee', 'jennifer.lee@hospital.com', '678-901-2345', '303 Cedar Ln, City', '1988-12-10', 'Female', 'ADMIN', 'Administration', 'Administrative Assistant', '2020-05-15', true),
    ('Robert Taylor', 'robert.taylor@hospital.com', '789-012-3456', '404 Birch Blvd, City', '1970-03-25', 'Male', 'DOCTOR', 'Gastroenterology', 'Gastroenterologist', '2008-11-01', true),
    ('Jessica Martinez', 'jessica.martinez@hospital.com', '890-123-4567', '505 Walnut St, City', '1983-09-15', 'Female', 'DOCTOR', 'Dermatology', 'Dermatologist', '2016-04-10', true),
    ('William Anderson', 'william.anderson@hospital.com', '901-234-5678', '606 Cherry Ave, City', '1978-06-08', 'Male', 'DOCTOR', 'Ophthalmology', 'Ophthalmologist', '2012-08-20', true),
    ('Amy Robinson', 'amy.robinson@hospital.com', '012-345-6789', '707 Spruce Ct, City', '1992-01-30', 'Female', 'NURSE', 'Pediatrics', 'Pediatric Nurse', '2021-03-05', true);

-- Insert staff qualifications
INSERT INTO staff_qualifications (staff_id, qualification)
VALUES
    (1, 'MD - Johns Hopkins University'),
    (1, 'Cardiology Residency - Mayo Clinic'),
    (1, 'Board Certified in Cardiology'),
    (2, 'MD - Stanford University'),
    (2, 'Neurology Residency - UCLA'),
    (2, 'Board Certified in Neurology'),
    (3, 'MD - Harvard Medical School'),
    (3, 'Orthopedic Surgery Residency - Massachusetts General Hospital'),
    (3, 'Fellowship in Sports Medicine'),
    (4, 'BSN - University of Washington'),
    (4, 'MSN - Georgetown University'),
    (5, 'BSN - University of Michigan'),
    (5, 'Critical Care Certification'),
    (7, 'MD - Yale School of Medicine'),
    (7, 'Gastroenterology Fellowship - Cleveland Clinic'),
    (8, 'MD - Columbia University'),
    (8, 'Dermatology Residency - NYU Langone'),
    (9, 'MD - University of Pennsylvania'),
    (9, 'Ophthalmology Residency - Wills Eye Hospital'),
    (10, 'BSN - Boston College'),
    (10, 'Pediatric Nursing Certification');

-- Insert staff specializations
INSERT INTO staff_specializations (staff_id, specialization)
VALUES
    (1, 'Interventional Cardiology'),
    (1, 'Cardiac Electrophysiology'),
    (1, 'Heart Failure Management'),
    (2, 'Epilepsy'),
    (2, 'Movement Disorders'),
    (2, 'Neurodegenerative Diseases'),
    (3, 'Sports Medicine'),
    (3, 'Joint Replacement'),
    (3, 'Trauma Surgery'),
    (4, 'Emergency Care'),
    (4, 'Trauma Nursing'),
    (5, 'Cardiac Care'),
    (5, 'Patient Education'),
    (7, 'Endoscopy'),
    (7, 'Inflammatory Bowel Disease'),
    (7, 'Liver Disease'),
    (8, 'Cosmetic Dermatology'),
    (8, 'Skin Cancer'),
    (8, 'Pediatric Dermatology'),
    (9, 'Cataract Surgery'),
    (9, 'Glaucoma Treatment'),
    (9, 'Retinal Disorders'),
    (10, 'Neonatal Care'),
    (10, 'Pediatric Oncology Nursing');

-- Insert schedules
INSERT INTO schedules (staff_id, start_time, end_time, shift_type, department, notes, is_active)
VALUES
    (1, '2025-04-17 08:00:00', '2025-04-17 16:00:00', 'REGULAR', 'Cardiology', 'Regular clinic hours', true),
    (1, '2025-04-18 08:00:00', '2025-04-18 16:00:00', 'REGULAR', 'Cardiology', 'Regular clinic hours', true),
    (2, '2025-04-17 09:00:00', '2025-04-17 17:00:00', 'REGULAR', 'Neurology', 'Patient consultations', true),
    (2, '2025-04-19 09:00:00', '2025-04-19 17:00:00', 'REGULAR', 'Neurology', 'Patient consultations', true),
    (3, '2025-04-18 07:00:00', '2025-04-18 15:00:00', 'SURGERY', 'Orthopedics', 'Scheduled surgeries', true),
    (3, '2025-04-20 07:00:00', '2025-04-20 15:00:00', 'REGULAR', 'Orthopedics', 'Follow-up appointments', true),
    (4, '2025-04-17 16:00:00', '2025-04-18 00:00:00', 'NIGHT', 'Emergency', 'Night shift coverage', true),
    (4, '2025-04-18 16:00:00', '2025-04-19 00:00:00', 'NIGHT', 'Emergency', 'Night shift coverage', true),
    (5, '2025-04-17 08:00:00', '2025-04-17 16:00:00', 'REGULAR', 'Cardiology', 'Assisting Dr. Smith', true),
    (5, '2025-04-19 08:00:00', '2025-04-19 16:00:00', 'REGULAR', 'Cardiology', 'Patient care', true),
    (7, '2025-04-17 10:00:00', '2025-04-17 18:00:00', 'REGULAR', 'Gastroenterology', 'Endoscopy procedures', true),
    (8, '2025-04-18 09:00:00', '2025-04-18 17:00:00', 'REGULAR', 'Dermatology', 'Skin cancer screenings', true),
    (9, '2025-04-19 08:00:00', '2025-04-19 16:00:00', 'SURGERY', 'Ophthalmology', 'Cataract surgeries', true),
    (10, '2025-04-17 07:00:00', '2025-04-17 15:00:00', 'REGULAR', 'Pediatrics', 'Pediatric care', true);

-- Insert workloads
INSERT INTO workloads (staff_id, date, patient_count, appointment_count, procedure_count, surgery_count, consultation_count, hours_worked, notes)
VALUES
    (1, '2025-04-15', 15, 15, 2, 0, 3, 8.5, 'Busy day with multiple consultations'),
    (1, '2025-04-16', 12, 12, 1, 0, 4, 8.0, 'Several new patient consultations'),
    (2, '2025-04-15', 10, 10, 1, 0, 2, 8.0, 'Regular patient load'),
    (2, '2025-04-16', 8, 8, 0, 0, 3, 7.5, 'Afternoon dedicated to research'),
    (3, '2025-04-16', 6, 6, 0, 3, 2, 9.0, 'Three scheduled surgeries completed'),
    (4, '2025-04-15', 22, 0, 5, 0, 0, 8.5, 'Busy night in emergency'),
    (4, '2025-04-16', 18, 0, 3, 0, 0, 8.0, 'Moderate emergency traffic'),
    (5, '2025-04-15', 15, 0, 3, 0, 0, 8.0, 'Assisted with cardiac procedures'),
    (7, '2025-04-16', 8, 8, 4, 0, 2, 8.5, 'Four endoscopy procedures'),
    (8, '2025-04-15', 14, 14, 3, 0, 5, 8.0, 'Multiple skin biopsies performed'),
    (9, '2025-04-16', 9, 9, 2, 4, 3, 9.5, 'Four cataract surgeries completed'),
    (10, '2025-04-15', 16, 16, 0, 0, 2, 8.0, 'Regular pediatric appointments');