package com.springboot.pms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties()
@Entity(name = "medical_history")
@Table
@NamedQuery(name = "Medical_History.PatientId", query = "from medical_history where patientId =:id")
public class Medical_History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int history_id;

	@NotEmpty
	@Column(name = "history_patient_id")
	private int patientId;

	@NotEmpty
	@PastOrPresent
	private LocalDate consultation_date;

	private String diagnosis;
}
