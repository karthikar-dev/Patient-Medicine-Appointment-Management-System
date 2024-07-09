package com.springboot.pms.controller;

import com.springboot.pms.model.*;
import com.springboot.pms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("patient")
public class PatientController {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	PrescriptionRepository prescriptionRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	MedicalHistoryRepository historyRepository;

	// **************************************************************
	// Registration controllers

	@GetMapping({ "register" })
	public String register(Model model) {
		model.addAttribute("patientForm", new Patient());
		return "register";
	}

	@PostMapping({ "register/save" })
	public String postRegister(@ModelAttribute Patient patient, Model model) {
		patientRepository.save(patient);
		return "redirect:/login";
	}

	// **********************************************************
	// Login Controllers

	// No Need to implement login features


	// Home page - Patient
	@GetMapping({ "{patientId}" })
	public String welcome(@PathVariable("patientId") int patientId, Model model) {
		String name = patientRepository.findById(patientId).get().getPatient_name();
		model.addAttribute("id", patientId);
		model.addAttribute("name", name);
		return "welcome";
	}

	@GetMapping({ "doctordetails" })
	public String getDoctorDetails(Model model) {
		List<Doctor> doctors = doctorRepository.findAll();
		model.addAttribute("doctors", doctors);

		return "doctor_details";
	}

	// **********************************************************
	// Appointment controller

	@GetMapping({ "bookappointment/{patientId}" })
	public String getPatientAppointmentBook(@PathVariable("patientId") int patientId, Model model) {
		Appointment appointment = new Appointment();
		appointment.setPatientId(patientId);

		Patient patient = patientRepository.findById(patientId).get();

		List<Doctor> doctors = doctorRepository.findAll();

		model.addAttribute("appointmentForm", appointment);
		model.addAttribute("doctors", doctors);
		model.addAttribute("patient", patient);

		// ****************************************************

		return "appointment_book";
	}

	@PostMapping({ "bookappointment/save" })
	public String postAppointmentBook(@ModelAttribute Appointment appointment, Model model) {

		appointmentRepository.save(appointment);

		int id = appointment.getPatientId();
		return "redirect:/patient/" + id;
	}

	@GetMapping({ "checkappointment/{patientId}" })
	public String getAppointmentCheck(@PathVariable("patientId") int patientId, Model model) {

		List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
		model.addAttribute("appointments", appointments);
		String name = patientRepository.findById(patientId).get().getPatient_name();
		model.addAttribute("id", patientId);
		model.addAttribute("name", name);

		// Doctor Names
//		List<String> doctors = new ArrayList<>();
//		for(Appointment a: appointments) {
//			doctors.add(doctorRepository.findById(a.getDoctorId()).get().getDoctor_name());
//		}
//		model.addAttribute("doctors",doctors);

		return "appointment_check";
	}

	// **********************************************************
	// Prescription controller

	@GetMapping({ "checkprescription/{patientId}" })
	public String getPrescriptionCheck(@PathVariable("patientId") int patientId, Model model) {
		List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
		model.addAttribute("prescriptions", prescriptions);

		String name = patientRepository.findById(patientId).get().getPatient_name();
		model.addAttribute("id", patientId);
		model.addAttribute("name", name);
		return "prescription_check";
	}

	// **********************************************************
	// Medical History controller

	@GetMapping({ "medicalhistory/{patientId}" })
	public String getMedicalHistory(@PathVariable("patientId") int patientId, Model model) {
		model.addAttribute("patient", patientRepository.findById(patientId).get());

		List<Medical_History> medHistory = historyRepository.findByPatientId(patientId);
		model.addAttribute("history", medHistory);

		return "medical_history";
	}

}
