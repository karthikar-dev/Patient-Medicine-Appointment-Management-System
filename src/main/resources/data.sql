-- Create pmsdb database
CREATE SCHEMA pmsdb;

-- Use pmsdb
SET SCHEMA pmsdb;

-- Schema for Doctor table
CREATE TABLE Doctor (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_name VARCHAR(24) NOT NULL,
    dob DATE NOT NULL,
    specialization VARCHAR(50) NOT NULL,
    sex CHAR(1) NOT NULL,
    mobile_no VARCHAR(10) NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    login_password VARCHAR(50) NOT NULL
);

-- Schema for Patient table
CREATE TABLE Patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(24) NOT NULL,
    dob DATE NOT NULL,
    sex CHAR(1) NOT NULL,
    mobile_no VARCHAR(10) NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    login_password VARCHAR(50) NOT NULL
);

-- Schema for Appointment table
CREATE TABLE Appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_doctor_id INT NOT NULL,
    appointment_patient_id INT NOT NULL,
    visit_date DATE NOT NULL,
    slot INT NOT NULL,
    booked BOOLEAN NOT NULL,
    FOREIGN KEY (appointment_doctor_id) REFERENCES Doctor(doctor_id),
    FOREIGN KEY (appointment_patient_id) REFERENCES Patient(patient_id)
);

-- Schema for Medical_History table
CREATE TABLE Medical_History (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    history_patient_id INT NOT NULL,
    consultation_date DATE NOT NULL,
    diagnosis VARCHAR(100),
    FOREIGN KEY (history_patient_id) REFERENCES Patient(patient_id)
);

-- Schema for Prescription table
CREATE TABLE Prescription (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    prescription_doctor_id INT NOT NULL,
    prescription_patient_id INT NOT NULL,
    issued_date_time TIMESTAMP NOT NULL,
    findings VARCHAR(255),
    medicines JSON NOT NULL,
    FOREIGN KEY (prescription_doctor_id) REFERENCES Doctor(doctor_id),
    FOREIGN KEY (prescription_patient_id) REFERENCES Patient(patient_id)
);


-- Doctor table
INSERT INTO Doctor (doctor_name, dob, specialization, sex, mobile_no, address, email, login_password) VALUES
('John Doe', '1975-05-20', 'Cardiology', 'M', '9123456780', '1234 Elm St, Springfield, IL', 'john.doe@gmail.com', 'password1'),
('Jane Smith', '1980-08-15', 'Neurology', 'F', '9234567890', '5678 Oak St, Springfield, IL', 'jane.smith@gmail.com', 'password2'),
('Sam Brown', '1970-01-30', 'Orthopedics', 'M', '9345678901', '9101 Pine St, Springfield, IL', 'sam.brown@gmail.com', 'password3'),
('Lucy Gray', '1985-03-25', 'Pediatrics', 'F', '9456789012', '1121 Maple St, Springfield, IL', 'lucy.gray@gmail.com', 'password4'),
('Peter Parker', '1978-11-11', 'Dermatology', 'M', '9567890123', '1314 Cedar St, Springfield, IL', 'peter.parker@gmail.com', 'password5'),
('Mary Jane', '1982-06-05', 'Gastroenterology', 'F', '9678901234', '1516 Birch St, Springfield, IL', 'mary.jane@gmail.com', 'password6'),
('Bruce Wayne', '1965-09-18', 'Oncology', 'M', '9789012345', '1718 Elm St, Springfield, IL', 'bruce.wayne@gmail.com', 'password7'),
('Diana Prince', '1972-12-25', 'Rheumatology', 'F', '9890123456', '1920 Oak St, Springfield, IL', 'diana.prince@gmail.com', 'password8'),
('Clark Kent', '1976-04-12', 'Hematology', 'M', '9901234567', '2122 Pine St, Springfield, IL', 'clark.kent@gmail.com', 'password9'),
('Lois Lane', '1983-07-22', 'Endocrinology', 'F', '9012345678', '2324 Maple St, Springfield, IL', 'lois.lane@gmail.com', 'password10');

-- Patient table
INSERT INTO Patient (patient_name, dob, sex, mobile_no, address, email, login_password) VALUES
('Karthika Rajaram', '1990-05-10', 'F', '8123456780', '1234 Elm St, Springfield, IL', 'karthika.rajaram8@gmail.com', 'password1'),
('Bob Williams', '1985-03-15', 'M', '8234567890', '5678 Oak St, Springfield, IL', 'bob.williams@gmail.com', 'password2'),
('Charlie Brown', '1995-11-30', 'M', '8345678901', '9101 Pine St, Springfield, IL', 'charlie.brown@gmail.com', 'password3'),
('Daisy Miller', '1988-07-25', 'F', '8456789012', '1121 Maple St, Springfield, IL', 'daisy.miller@gmail.com', 'password4'),
('Eve Davis', '1992-10-11', 'F', '8567890123', '1314 Cedar St, Springfield, IL', 'eve.davis@gmail.com', 'password5'),
('Frank Harris', '1984-06-05', 'M', '8678901234', '1516 Birch St, Springfield, IL', 'frank.harris@gmail.com', 'password6'),
('Grace Lee', '1996-09-18', 'F', '8789012345', '1718 Elm St, Springfield, IL', 'grace.lee@gmail.com', 'password7'),
('Henry Clark', '1982-12-25', 'M', '8890123456', '1920 Oak St, Springfield, IL', 'henry.clark@gmail.com', 'password8'),
('Ivy Walker', '1994-04-12', 'F', '8901234567', '2122 Pine St, Springfield, IL', 'ivy.walker@gmail.com', 'password9'),
('Jack Robinson', '1987-07-22', 'M', '8012345678', '2324 Maple St, Springfield, IL', 'jack.robinson@gmail.com', 'password10');

-- Appointment table
INSERT INTO Appointment (appointment_doctor_id, appointment_patient_id, visit_date, slot, booked) VALUES
(1, 1, '2023-01-01', 1, true),
(1, 1, '2023-03-05', 5, true),
(1, 1, '2023-05-10', 6, true),
(1, 1, '2023-07-01', 2, true),
(2, 2, '2023-01-02', 2, true),
(3, 3, '2023-01-03', 3, false),
(4, 4, '2023-01-04', 4, true),
(5, 5, '2023-01-05', 5, false),
(6, 6, '2023-01-06', 1, true),
(7, 7, '2023-01-07', 2, false),
(8, 8, '2023-01-08', 3, true),
(9, 9, '2023-01-09', 4, false),
(10, 10, '2023-01-10', 5, true);

-- Medical History table
INSERT INTO Medical_History (history_patient_id, consultation_date, diagnosis) VALUES
(1, '2023-01-01', 'Hypertension'),
(1, '2023-03-05', 'Hypertension'),
(1, '2023-05-10', 'Hypertension'),
(1, '2023-07-01', 'Hypertension'),
(2, '2023-01-02', 'Diabetes Mellitus'),
(3, '2023-01-03', 'Osteoarthritis'),
(4, '2023-01-04', 'Asthma'),
(5, '2023-01-05', 'Hypothyroidism'),
(6, '2023-01-06', 'Chronic Kidney Disease'),
(7, '2023-01-07', 'Rheumatoid Arthritis'),
(8, '2023-01-08', 'Chronic Obstructive Pulmonary Disease'),
(9, '2023-01-09', 'Anemia'),
(10, '2023-01-10', 'Migraine');

-- Prescription table
INSERT INTO Prescription (prescription_doctor_id, prescription_patient_id, issued_date_time, findings, medicines) VALUES
(1, 1, '2023-01-01', 'Blood pressure elevated', 'Amlodipine: 5 mg once daily'),
(1, 1, '2023-03-05', 'Blood pressure elevated', 'Amlodipine: 5 mg once daily'),
(1, 1, '2023-05-10', 'Blood pressure elevated', 'Amlodipine: 5 mg once daily'),
(1, 1, '2023-07-01', 'Blood pressure elevated', 'Amlodipine: 5 mg once daily'),
(2, 2, '2023-01-02', 'High blood sugar levels', 'Metformin: 500 mg twice daily'),
(3, 3, '2023-01-03', 'Joint pain and stiffness', 'Ibuprofen: 400 mg every 6 hours as needed'),
(4, 4, '2023-01-04', 'Shortness of breath', 'Albuterol: 2 puffs every 4 hours as needed'),
(5, 5, '2023-01-05', 'Low thyroid function', 'Levothyroxine: 50 mcg once daily'),
(6, 6, '2023-01-06', 'Decreased kidney function', 'Losartan: 50 mg once daily'),
(7, 7, '2023-01-07', 'Joint inflammation', 'Methotrexate: 10 mg once weekly'),
(8, 8, '2023-01-08', 'Difficulty breathing', 'Tiotropium: 18 mcg once daily'),
(9, 9, '2023-01-09', 'Low hemoglobin levels', 'Ferrous Sulfate: 325 mg twice daily'),
(10, 10, '2023-01-10', 'Severe headaches', 'Sumatriptan: 50 mg as needed for migraine');
