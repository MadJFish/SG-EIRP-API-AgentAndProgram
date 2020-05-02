package com.sg.eirp.program.dto;

import java.util.List;

public class TrainerDetailDto {
	private String id;

	private String name;

	private String imageUrl;

	private List<String> photos;

	private List<String> videoUrls;

	private String promoText;

	private Boolean featured;

	private List<String> targetEduLevels;

	private String phone;

	private String email;

	private String aboutUs;

	private List<ProgramDto> programs;

	private TrainerHQAddressDto hqAddress;

	private List<TrainerBranchDto> branches;

	private List<WhatsUpNewsDto> whatsUpNews;

	private List<TutorDto> teachers;

	private List<TrainerLeadershipDto> leadershipTeam;
}
