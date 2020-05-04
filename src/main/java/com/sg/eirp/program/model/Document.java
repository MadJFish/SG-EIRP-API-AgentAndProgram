package com.sg.eirp.program.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tb_document")
public class Document {
    @Id
    @GeneratedValue
    @Column(name = "document_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String filename;

    @Column
    private String filetype;

    @Column
    private String mime;

    @Column
    private String url;

    @Column(name = "reference_table")
    private String referenceTable;

    @Column(name = "reference_id")
    private UUID referenceId;
}
