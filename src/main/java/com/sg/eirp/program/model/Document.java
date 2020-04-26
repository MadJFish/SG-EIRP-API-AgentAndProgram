package com.sg.eirp.program.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Document {
	@Id
    @GeneratedValue
    @Column(name = "document_id",columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;
	
	@Column(name = "reference_table", nullable = false)
	private String referenceTable;
	
	@Column(name = "reference_id", nullable = false)
	private String referenceId;
	
	@Column(name = "document_type")
	private String documentType;
	
	@Column
	private String mime;
	
	@Column
	private String url;
}
