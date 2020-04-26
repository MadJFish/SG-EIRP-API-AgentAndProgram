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
@Table(name="tb_agency_branch")
public class AgencyBranch {
	@Id
    @GeneratedValue
    @Column(name = "agency_branch_id",columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column(name = "agency_id")
	private UUID agencyId;
	
	@Column
	private String name;
	
	@Column
	private String contact;
	
	@Column
	private String fax;
	
	@Column
	private String email;
	
	@Column(name = "nearby_mrt")
	private String nearbyMRT;
	
	@Column(name = "address_id")
	private UUID addressId;
}
