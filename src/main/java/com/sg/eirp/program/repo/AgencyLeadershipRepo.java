package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.AgencyLeadership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyLeadershipRepo extends CrudRepository<AgencyLeadership, UUID> {

    @Query(value = "SELECT a FROM AgencyLeadership a where a.agencyId = ?1")
    Optional<List<AgencyLeadership>> getByAgencyId(UUID uuid);

}
