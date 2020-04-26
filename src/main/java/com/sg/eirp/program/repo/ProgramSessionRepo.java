package com.sg.eirp.program.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sg.eirp.program.model.ProgramSession;

public interface ProgramSessionRepo extends CrudRepository<ProgramSession, UUID> {
	
	@Query("SELECT p FROM ProgramSession p where p.programId = :programId")
	Optional<List<ProgramSession>> findByProgramId(UUID programId);
}
