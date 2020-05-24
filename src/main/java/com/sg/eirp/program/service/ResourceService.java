package com.sg.eirp.program.service;

// import com.sg.eirp.common.dto.common.ResourceDto;
import com.sg.eirp.program.dto.ResourceDto;

import java.util.List;
import java.util.Optional;

public interface ResourceService {
    Optional<List<ResourceDto>> getResourceByUserRole(String userRole);
}
