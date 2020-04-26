package com.sg.eirp.program.model;

import java.sql.Timestamp;
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
@Table(name="tb_program_session")
public class ProgramSession {
	
	@Id
    @GeneratedValue
    @Column(name = "session_id",columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column(name = "program_id", nullable = false)
	private UUID programId;
    
    @Column (name = "start_datetime")
    private Timestamp startDatetime;
    
    @Column (name = "end_datetime")
    private Timestamp endDatetime;
    
    @Column
    private int quota;
    
    @Column (name = "address_id")
    private String addressId;
}
