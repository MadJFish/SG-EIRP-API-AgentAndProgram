package com.sg.eirp.program.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tb_bulletin")
public class Bulletin {
    @Id
    @GeneratedValue
    @Column(name = "bulletin_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "publish_date")
    private Timestamp publishedDate;

    @Column(columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(columnDefinition = "VARCHAR(4000)")
    private String content;

    @Column(name = "reference_table", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
    private String referenceTable;

    @Column(name = "reference_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID referenceId;
}
