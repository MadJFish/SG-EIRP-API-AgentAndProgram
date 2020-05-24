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
@Table(name="tb_resource")
public class Resource {
    @Id
    @GeneratedValue
    @Column(name = "resource_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column(columnDefinition = "VARCHAR(20)")
    private String type;

    @Column(name = "resource_owner", columnDefinition = "VARCHAR(50)")
    private String resourceOwner;

    @Column(columnDefinition = "VARCHAR(50)")
    private String resource;
}
