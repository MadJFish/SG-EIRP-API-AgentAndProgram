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
@Table(name="tb_code")
public class Code {
    @Id
    @GeneratedValue
    @Column(name = "code_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String type;

    @Column
    private String value;

    @Column
    private String description;
}
