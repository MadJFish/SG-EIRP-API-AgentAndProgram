package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.program.mapper.AgencyBranchMapper;
import com.sg.eirp.program.model.Agency;
import com.sg.eirp.program.model.AgencyBranch;
import com.sg.eirp.program.repo.AgencyBranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(rollbackFor = Exception.class)
@Service
public class AgencyBranchServiceImpl implements AgencyBranchService {

    @Autowired
    private AgencyBranchRepo agencyBranchRepo;

    @Autowired
    private AgencyBranchMapper agencyBranchMapper;

    @Override
    public TutorAgencyBranchDto save(TutorAgencyBranchDto dto, Agency agency) {
        if (dto == null || agency == null) {
            return null;
        }

        AgencyBranch agencyBranch = agencyBranchMapper.dtoToEntity(dto);
        if (agencyBranch != null) {
            agencyBranch.setAgency(agency);
            agencyBranch = agencyBranchRepo.save(agencyBranch);
        }

        return agencyBranchMapper.entityToDto(agencyBranch);
    }
}
