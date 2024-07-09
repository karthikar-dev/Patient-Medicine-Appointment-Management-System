package com.springboot.pms.repository;

import com.springboot.pms.model.Doctor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Tag(name = "Record: Doctor - Rest API Controllers", description = "Doctor Record API")
@RepositoryRestResource(collectionResourceRel = "doctor", path = "doctor")
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	public Doctor findByEmail(@Param("email") String email);

}
