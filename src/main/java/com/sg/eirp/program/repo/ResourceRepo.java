package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceRepo extends CrudRepository<Resource, UUID> {

    @Query("SELECT r FROM Resource r WHERE r.type = :type AND r.resourceOwner = :resourceOwner")
    Optional<List<Resource>> findByTypeAndResourceOwner(String type, String resourceOwner);

}
