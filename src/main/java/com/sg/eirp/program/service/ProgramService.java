package com.sg.eirp.program.service;

import java.util.List;

import com.sg.eirp.program.model.Program;

public interface ProgramService {
	Program save(Program program);
	
	List<Program> getPrograms();
}
