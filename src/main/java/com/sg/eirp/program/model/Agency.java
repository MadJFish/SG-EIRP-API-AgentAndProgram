package com.sg.eirp.program.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tb_agency")
public class Agency {
	
	@Id
    @GeneratedValue
    @Column(name = "agency_id",columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column
	private String name;
	
	@Column (name = "image_url")
	private String imageUrl;
	
	@Column (name = "video_url")
	private String videoUrl;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column (name = "is_featured")
	private boolean isFeatured;
	
	@Column (name = "education_level")
	private String targetEducationLevel;
	
	@Column
	private String contact;
	
	@Column
	private String fax;
	
	@Column
	private String email;
	
	@Column
	private String subjects;
	
	@Column (name = "nearby_mrt")
	private String nearbyMRT;
	
	@Column (name = "address_id")
	private String addressId;
}
