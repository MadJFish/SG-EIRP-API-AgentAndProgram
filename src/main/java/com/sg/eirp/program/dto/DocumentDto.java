package com.sg.eirp.program.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private String documentId;

    private String documentName;

    private String documentType;

    private String mime;

    private String documentUrl;
}
