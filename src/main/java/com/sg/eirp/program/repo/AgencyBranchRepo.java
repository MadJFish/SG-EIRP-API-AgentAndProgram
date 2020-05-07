package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.AgencyBranch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyBranchRepo extends CrudRepository<AgencyBranch, UUID> {

    @Query(value = "SELECT * FROM tb_agency_branch a where a.agency_id = ?1", nativeQuery = true)
    Optional<List<AgencyBranch>> getByAgencyId(UUID uuid);

}
