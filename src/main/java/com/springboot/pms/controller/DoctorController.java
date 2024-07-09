package com.springboot.pms.controller;

import com.springboot.pms.model.Appointment;
import com.springboot.pms.model.Medical_History;
import com.springboot.pms.model.Patient;
import com.springboot.pms.model.Prescription;
import com.springboot.pms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	PrescriptionRepository prescriptionRepository;

	@Autowired
	MedicalHistoryRepository medicalHistoryRepository;

	// ************************************************************
	// Home Controllers

	// Home page - Doctor
	@GetMapping({ "{docId}" })
	public String welcome(@PathVariable("docId") int docId, Model model) {

		String name = doctorRepository.findById(docId).get().getDoctor_name();
		model.addAttribute("id", docId);
		model.addAttribute("name", name);

		return "doc_welcome";
	}

	// ************************************************************
	// Appointment controllers

	@GetMapping({ "checkappointment/{docId}" })
	public String getAppointmentCheck(@PathVariable("docId") int docId, Model model) {

		String name = doctorRepository.findById(docId).get().getDoctor_name();
		model.addAttribute("id", docId);
		model.addAttribute("name", name);

		List<Appointment> appointments = appointmentRepository.findByDoctorId(docId);
		model.addAttribute("appointments", appointments);

		return "doc_appointment_check";
	}

	@GetMapping({ "cancelappointment/{appId}" })
	public String getAppointmentCancel(@PathVariable("appId") int appId, Model model) {

		Appointment appointment = appointmentRepository.findById(appId).get();
		model.addAttribute("appointment", appointment);

		return "doc_appointment_cancel";
	}

	@PostMapping({ "cancelappointment/{appId}" })
	public String postAppointmentCancel(@PathVariable("appId") int appId) {

		int docId = appointmentRepository.findById(appId).get().getDoctorId();
		appointmentRepository.deleteById(appId);

		return "redirect:/doctor/" + docId;
	}

	// ************************************************************
	// Prescription controllers

	@GetMapping({ "issueprescription/{appId}" })
	public String getPrescriptionIssue(@PathVariable("appId") int appId, Model model) {
		Appointment app = appointmentRepository.findById(appId).get();
		Prescription prescription = new Prescription();

		prescription.setDoctorId(app.getDoctorId());
		prescription.setPatientId(app.getPatientId());
		prescription.setIssuedDateTime(LocalDateTime.now());
		model.addAttribute("prescriptionForm", prescription);

		int patientId = app.getPatientId();
		String name = patientRepository.findById(patientId).get().getPatient_name();
		model.addAttribute("id", patientId);
		model.addAttribute("name", name);

		return "doc_prescription_issue";
	}

	@PostMapping({ "issueprescription/save" })
	public String postPrescriptionIssue(@ModelAttribute Prescription prescription) {

		prescriptionRepository.save(prescription);
		return "redirect:/doctor/" + prescription.getDoctorId();
	}

	@GetMapping({ "issuedprescription/{patientId}" })
	public String getPrescriptionIssued(@PathVariable("patientId") int patientId, Model model) {
		String name = patientRepository.findById(patientId).get().getPatient_name();
		model.addAttribute("id", patientId);
		model.addAttribute("name", name);

		List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
		model.addAttribute("prescriptions", prescriptions);

		return "doc_prescription_check";
	}

	@GetMapping({ "modifyprescription/{prescId}" })
	public String getPrescriptionModify(@PathVariable("prescId") int prescId, Model model) {
		int patientId = prescriptionRepository.findById(prescId).get().getPatientId();
		String name = patientRepository.findById(patientId).get().getPatient_name();
		model.addAttribute("id", patientId);
		model.addAttribute("name", name);

		Prescription prescription = prescriptionRepository.findById(prescId).get();
		prescription.setPrescription_id(prescId);
		prescription.setIssuedDateTime(LocalDateTime.now());
		model.addAttribute("modifyPrescriptionForm", prescription);

		return "doc_prescription_modify";
	}

	@PostMapping({ "modifyprescription/save" })
	public String postPrescriptionModify(@ModelAttribute("prescription") Prescription prescription) {

		prescriptionRepository.save(prescription);

		return "redirect:/doctor/" + prescription.getDoctorId();
	}

	@GetMapping({ "deleteprescription/{prescId}" })
	public String getPrescriptionRemove(@PathVariable("prescId") int prescId, Model model) {

		model.addAttribute("prescription", prescriptionRepository.findById(prescId).get());

		return "doc_prescription_remove";
	}

	@PostMapping({ "deleteprescription/{prescId}" })
	public String postPrescriptionRemove(@PathVariable("prescId") int prescId) {
		int docId = prescriptionRepository.findById(prescId).get().getDoctorId();
		prescriptionRepository.deleteById(prescId);

		return "redirect:/doctor/" + docId;
	}
	// ************************************************************
	// Medical History - Controllers

	@GetMapping({ "medicalhistory/{patientId}" })
	public String getDocMedicalHistory(@PathVariable("patientId") int patientId, Model model) {

		Patient patient = patientRepository.findById(patientId).get();
		model.addAttribute("patient", patient);

		List<Medical_History> medHistory = medicalHistoryRepository.findByPatientId(patientId);
		model.addAttribute("history", medHistory);

		return "medical_history";
	}

	@GetMapping({ "updatemedicalhistory/{appId}" })
	public String getMedicalHistory(@PathVariable("appId") int appId, Model model) {

//		Appointment app = appointmentRepository.findById(appId).get();
//		int patientId = app.getPatientId();
		String name = patientRepository.findById(appId).get().getPatient_name();

		model.addAttribute("id", appId);
		model.addAttribute("name", name);

		Medical_History hist = new Medical_History();
		hist.setPatientId(appId);
		model.addAttribute("history", hist);

		return "doc_medical_history";
	}

	@PostMapping({ "updatemedicalhistory/save" })
	public String updateMedicalHistory(@ModelAttribute Medical_History medHis) {
		medHis.setConsultation_date(LocalDate.now());
		medicalHistoryRepository.save(medHis);

		int patientId = medHis.getPatientId();
		return "redirect:/doctor/" + patientId;
	}
}
