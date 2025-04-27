-- data.sql
INSERT INTO Admin (subject, password, role) VALUES ('nphung', '$2b$12$0tTBPjg98l7tVZ5hTB.lU.TrpLJ6gAzJLoHU4g9SWIxB6O03SXCIS', 'ADMIN');
INSERT INTO Admin (subject, password, role) VALUES ('nphung214', '$2b$12$0tTBPjg98l7tVZ5hTB.lU.TrpLJ6gAzJLoHU4g9SWIxB6O03SXCIS', 'ADMIN');

INSERT INTO Patient (subject, password, role) VALUES ('patient1@example.com', '$2b$12$0tTBPjg98l7tVZ5hTB.lU.TrpLJ6gAzJLoHU4g9SWIxB6O03SXCIS', 'PATIENT');
INSERT INTO Patient (subject, password, role) VALUES ('patient2@example.com', '$2b$12$0tTBPjg98l7tVZ5hTB.lU.TrpLJ6gAzJLoHU4g9SWIxB6O03SXCIS', 'PATIENT');

INSERT INTO Staff (subject, password, role) VALUES ('staff1@example.com', '$2b$12$0tTBPjg98l7tVZ5hTB.lU.TrpLJ6gAzJLoHU4g9SWIxB6O03SXCIS', 'STAFF');
INSERT INTO Staff (subject, password, role) VALUES ('staff2@example.com', '$2b$12$0tTBPjg98l7tVZ5hTB.lU.TrpLJ6gAzJLoHU4g9SWIxB6O03SXCIS', 'STAFF');

