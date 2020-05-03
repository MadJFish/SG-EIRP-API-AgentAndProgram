package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.program.model.Agency;

public interface AgencyBranchService {
    TutorAgencyBranchDto save(TutorAgencyBranchDto dto, Agency agency);
}
