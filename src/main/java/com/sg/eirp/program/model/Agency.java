package com.sg.eirp.program.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name = "promo_text")
	private String promoText;
	
	@Column
	private String description;
	
	@Column (name = "is_featured")
	private String featured;
	
	@Column (name = "education_level")
	private String targetEducationLevel;
	
	@Column
	private String contact;
	
	@Column
	private String email;

	@Column(name = "about_us")
	private String aboutUs;

	@Column
	private String subjects;
	
	@Column (name = "nearby_mrt")
	private String nearbyMRT;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "hq_address_id", referencedColumnName = "address_id")
	private Address hqAddress;
	
	// @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    // private Set<AgencyBranch> branches = new HashSet<>();
}
