
-- Insert sample patients
INSERT INTO patients (full_name, email, phone_number, address, date_of_birth, gender, national_id, blood_type, registration_date, is_active)
VALUES
    ('Alice Johnson', 'alice.johnson@email.com', '+1234567891', '789 Pine St, City', '1990-05-10', 'FEMALE', 'NAT001', 'A+', '2025-01-15', TRUE),
    ('Bob Williams', 'bob.williams@email.com', '+1234567892', '456 Oak St, City', '1985-11-22', 'MALE', 'NAT002', 'B-', '2025-02-01', TRUE),
    ('Carol Brown', 'carol.brown@email.com', '+1234567893', '123 Maple St, City', '1978-03-30', 'FEMALE', 'NAT003', 'O+', '2025-03-10', TRUE),
    ('David Lee', 'david.lee@email.com', '+1234567894', '321 Birch St, City', '1995-07-15', 'MALE', 'NAT004', 'AB+', '2025-04-01', FALSE);

-- Insert sample medical histories
INSERT INTO medical_histories (patient_id, diagnosis, treatment, medication, allergies, visit_date, doctor_id, notes)
VALUES
    (1, 'Hypertension', 'Lifestyle changes, medication', 'Lisinopril', 'None', '2025-01-20', 1, 'Monitor blood pressure weekly'),
    (1, 'Flu', 'Rest, hydration', 'Paracetamol', 'None', '2025-02-10', 2, 'Follow-up in 7 days'),
    (2, 'Type 2 Diabetes', 'Insulin therapy', 'Metformin', 'Penicillin', '2025-02-15', 1, 'Regular glucose monitoring'),
    (3, 'Asthma', 'Inhaler use', 'Albuterol', 'Dust mites', '2025-03-12', 2, 'Avoid allergens'),
    (4, 'Sprained Ankle', 'Rest, ice, compression', 'Ibuprofen', 'None', '2025-04-05', 1, 'Physical therapy recommended');

-- Insert sample patient insurance
INSERT INTO patient_insurance (patient_id, insurance_provider, policy_number, valid_from, valid_until)
VALUES
    (1, 'HealthCare Inc.', 'POL123456', '2025-01-01', '2025-12-31'),
    (2, 'MediCover', 'POL789012', '2025-02-01', '2026-01-31'),
    (3, 'SafeHealth', 'POL345678', '2025-03-01', '2025-12-31');