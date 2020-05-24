package com.sg.eirp.program.mapper;

// import com.sg.eirp.common.dto.common.ResourceDto;
import com.sg.eirp.program.dto.ResourceDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Resource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResourceMapper extends DtoEntityMapper<ResourceDto, Resource> {
    @Override
    public Resource dtoToEntity(ResourceDto resourceDto) {
        if (resourceDto == null) {
            return null;
        }

        Resource resource = new Resource();

        if (resourceDto.getId() != null) {
            resource.setId(UUID.fromString(resourceDto.getId()));
        }

        resource.setType(resourceDto.getType());

        resource.setResourceOwner(resourceDto.getResourceOwner());

        resource.setResource(resourceDto.getResource());

        return resource;
    }

    @Override
    public ResourceDto entityToDto(Resource resource) {
        if (resource == null) {
            return null;
        }

        ResourceDto resourceDto = new ResourceDto();

        if (resource.getId() != null) {
            resourceDto.setId(resource.getId().toString());
        }

        resourceDto.setType(resource.getType());

        resourceDto.setResourceOwner(resource.getResourceOwner());

        resourceDto.setResource(resource.getResource());

        return resourceDto;
    }
}
