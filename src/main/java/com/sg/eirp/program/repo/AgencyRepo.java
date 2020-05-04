package com.sg.eirp.program.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sg.eirp.program.model.Code;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sg.eirp.program.model.Agency;

public interface AgencyRepo extends CrudRepository<Agency, UUID> {
    @Query("SELECT 1 FROM Agency a where a.id = :id")
    boolean existsById(UUID id);
}
