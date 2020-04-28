package com.sg.eirp.program.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDto {
	private String programId;
	
	private String programName;
	
	private String programDescription;
	
	private String rogramDetailParagraph;
	
	private String imageUrl;
	
	private String subject;
	
	private String forEducationLevel;
	
	private Integer forMinAge;
	
	private Integer forMaxAge;
	
	private String duration;
	
	private String cost;
	
	private List<String> locations;
	
	private String trainerId;
}
