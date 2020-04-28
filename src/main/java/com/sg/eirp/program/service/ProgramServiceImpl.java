package com.sg.eirp.program.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.eirp.program.model.Program;
import com.sg.eirp.program.repo.ProgramRepo;

@Transactional(rollbackFor = Exception.class)
@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramRepo programRepo;
	
	public ProgramServiceImpl() {
	}
	
	@Override
	public Program save(Program program) {
		return programRepo.save(program);
	}

	@Override
	public List<Program> getPrograms() {
		Iterable<Program> programIterable = programRepo.findAll();
		
		// convert the iterable to list
        List<Program> programList = StreamSupport.stream(programIterable.spliterator(), false)
        								.collect(Collectors.toList());
        
        return programList;
	}

}
