package com.sg.eirp.program.service;

// import com.sg.eirp.common.dto.common.ResourceDto;
import com.sg.eirp.program.dto.ResourceDto;
import com.sg.eirp.program.mapper.ResourceMapper;
import com.sg.eirp.program.repo.ResourceRepo;
import com.sg.eirp.program.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Optional<List<ResourceDto>> getResourceByUserRole(String userRole) {
        return resourceMapper.entitiesToDtos(resourceRepo.findByTypeAndResourceOwner(CommonConstants.CODE_TYPE_RESOURCE, userRole));
    }
}
