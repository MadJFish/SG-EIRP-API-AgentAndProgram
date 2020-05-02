package com.sg.eirp.program.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
	
	@Column
	private String name;
	
	@Column
	private String contact;
	
	@Column
	private String email;
	
	@Column(name = "nearby_mrt")
	private String nearbyMRT;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
	private Address addressId;
}
