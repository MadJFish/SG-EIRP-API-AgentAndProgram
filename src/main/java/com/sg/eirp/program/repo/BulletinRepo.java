package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.Bulletin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BulletinRepo extends CrudRepository<Bulletin, UUID> {

    @Query("SELECT b FROM Bulletin b where b.referenceTable = :referenceTable and b.referenceId = :referenceId")
    Optional<List<Bulletin>> findByReferenceId(String referenceTable, UUID referenceId);

}
