package com.sg.eirp.program.repo;

import com.sg.eirp.program.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepo extends CrudRepository<Address, UUID> {
}
