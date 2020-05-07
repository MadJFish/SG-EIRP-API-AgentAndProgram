package com.sg.eirp.program.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tb_program")
public class Program {
	
	@Id
    @GeneratedValue
    @Column(name = "program_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column(name = "agency_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID agencyId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String details;

    @Column
    private String subject;

    @Column (name = "education_level")
    private String educationLevel;
    
    @Column (name = "min_age")
    private int minAge;
    
    @Column (name = "max_age")
    private int maxAge;
    
    @Column (name = "fee_type")
    private String feeType;
    
    @Column
    private BigInteger fee;
    
    @Column (name = "fee_currency")
    private String feeCurrency;

    @Column
    private String locations;
}