package com.springboot.pms.repository;

import com.springboot.pms.model.Medical_History;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Tag(name = "Medical History - Rest API Controllers", description = "Medical History API")
@RepositoryRestResource(collectionResourceRel = "medical_history", path = "medical-history")
public interface MedicalHistoryRepository extends JpaRepository<Medical_History, Integer> {

	// To collect all history of a particular Patient using patient_id
	// @Query("from medical_history where history_patient_id =:id") - Does not work

	// NamedQuery Annotation added for the Entity class - Customizable query
	// id from named query is binded with the below parameter
	public List<Medical_History> findByPatientId(@Param("id") int id);

}