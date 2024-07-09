package com.springboot.pms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "appointment")
@Table
@NamedQuery(name = "Appointment.PatientId", query = "from appointment where patientId =:id")
@NamedQuery(name = "Appointment.DoctorId", query = "from appointment where doctorId =:id")
@NamedQuery(name = "Appointment.DateSlot", query = "from appointment where doctorId =:id and visitDate =:vdate")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointment_id;

	@Column(name = "appointment_doctor_id")
	private int doctorId;

	@Column(name = "appointment_patient_id")
	private int patientId;

	@PastOrPresent
	@Column(name = "visit_date")
	private LocalDate visitDate;

	private int slot;

	private boolean booked;
}
