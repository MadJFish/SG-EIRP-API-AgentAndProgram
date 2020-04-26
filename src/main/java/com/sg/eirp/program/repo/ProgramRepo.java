package com.sg.eirp.program.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.sg.eirp.program.model.Program;

public interface ProgramRepo extends CrudRepository<Program, UUID> {
}
