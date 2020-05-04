package com.sg.eirp.program.service;

import com.sg.eirp.program.model.Code;

import java.util.List;

public interface CodeService {
    List<Code> getCodesByType(String type);
}
