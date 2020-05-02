package com.sg.eirp.program.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.sg.eirp.program.model.Agency;

public interface AgencyRepo extends CrudRepository<Agency, UUID> {

}
