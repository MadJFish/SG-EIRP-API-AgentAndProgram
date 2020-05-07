package com.sg.eirp.program.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sg.eirp.program.model.Program;

public interface ProgramRepo extends CrudRepository<Program, UUID> {

    @Query("SELECT p FROM Program p where p.id = :id")
    Optional<Program> getById(UUID id);

    @Query("SELECT p FROM Program p where p.agencyId = ?1")
    Optional<List<Program>> getByAgencyId(UUID agencyId);

}
