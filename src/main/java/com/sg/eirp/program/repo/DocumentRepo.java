package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepo extends CrudRepository<Document, UUID> {
    @Query("SELECT d FROM Document d where d.referenceTable = :referenceTable and d.referenceId = :referenceId")
    Optional<List<Document>> findByReferenceId(String referenceTable, String referenceId);
}
