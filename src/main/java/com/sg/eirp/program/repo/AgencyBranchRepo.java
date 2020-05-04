package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.AgencyBranch;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AgencyBranchRepo extends CrudRepository<AgencyBranch, UUID> {
}
