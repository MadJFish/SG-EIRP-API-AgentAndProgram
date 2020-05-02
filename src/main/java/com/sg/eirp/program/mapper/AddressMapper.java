package com.sg.eirp.program.mapper;

import com.sg.eirp.program.base.BaseMapper;
import com.sg.eirp.program.dto.AddressDto;
import com.sg.eirp.program.dto.TrainerHQAddressDto;
import com.sg.eirp.program.model.Address;
import com.sg.eirp.program.model.Agency;

public class AddressMapper extends BaseMapper {

    @Override
    public Object mapObject(Object model) {
        if (model instanceof Address) {
            Address address = (Address) model;
            AddressDto dto = new AddressDto();
            dto.setId(address.getId().toString());
            dto.setBlockNo(address.getBlockNo());
            dto.setLatitude(address.getLatitude());
            dto.setLongitude(address.getLongitude());
            dto.setPostalCode(address.getPostalCode());
            dto.setStreet(address.getStreet());
            dto.setUnitNo(address.getUnitNo());
            return dto;
        }
        return null;
    }

    public Object mapTrainerHQAddress(Address address, Agency agency) {
        if (address == null) {
            return null;
        }

        Object resultObj = mapObject(agency);

        TrainerHQAddressDto hqAddressDto = new TrainerHQAddressDto();
        if (resultObj != null) {
            AddressDto addressDto = (AddressDto) resultObj;
            hqAddressDto.setStreet(addressDto.getStreet());
            hqAddressDto.setPostalCode(addressDto.getPostalCode());
            hqAddressDto.setLatitude(addressDto.getLatitude());
            hqAddressDto.setLongitude(addressDto.getLongitude());

            if (agency != null) {
                hqAddressDto.setNearbyMRT(agency.getNearbyMRT());
            }

            return hqAddressDto;
        }

        return null;
    }
}
