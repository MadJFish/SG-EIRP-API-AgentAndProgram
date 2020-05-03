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
@Table(name = "tb_address")
public class Address {
	
	@Id
    @GeneratedValue
    @Column(name = "address_id",columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column(name = "block_no")
	private String blockNo;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column
	private String street;
	
	@Column (name = "unit_no")
	private String unitNo;
}
