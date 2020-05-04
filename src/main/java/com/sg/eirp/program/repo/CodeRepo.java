package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.Code;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeRepo extends CrudRepository<Code, UUID> {

    @Query("SELECT c FROM Code c where c.type = :type")
    Optional<List<Code>> findByType(String type);

}
