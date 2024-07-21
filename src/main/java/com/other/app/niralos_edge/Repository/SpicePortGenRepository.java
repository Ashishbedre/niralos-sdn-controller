package com.other.app.niralos_edge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.SpicePortGenModel;

public interface SpicePortGenRepository extends JpaRepository<SpicePortGenModel, Long> {
	
	@Query("SELECT COUNT(v) FROM SpicePortGenModel v")
	public Integer getDataExist();
	
	@Query("SELECT v.number FROM SpicePortGenModel v")
	public Integer getData();
}

