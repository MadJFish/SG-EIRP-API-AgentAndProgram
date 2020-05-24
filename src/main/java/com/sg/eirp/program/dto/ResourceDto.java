package com.sg.eirp.program.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    private String id;

    private String type;

    private String resourceOwner;

    private String resource;

}
