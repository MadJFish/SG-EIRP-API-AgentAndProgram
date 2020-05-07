package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.user.AddressDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Address;
import com.sg.eirp.program.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends DtoEntityMapper<AddressDto, Address> {

    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);

        if (addressDto.getId() != null) {
            address.setId(CommonUtil.convertIdtoUUID(addressDto.getId()));
        }

        return address;
    }

    @Override
    public AddressDto entityToDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);

        if (address.getId() != null) {
            addressDto.setId(address.getId().toString());
        }

        return addressDto;
    }
}
