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
@Table(name="tb_agency_leadership")
public class AgencyLeadership {
	@Id
    @GeneratedValue
    @Column(name = "agency_leadership_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column(name = "agency_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID agencyId;
	
	@Column
	private String name;
	
	@Column
	private String designation;
	
	@Column
	private String description;
}
