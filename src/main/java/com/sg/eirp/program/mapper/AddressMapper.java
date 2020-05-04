package com.sg.eirp.program.mapper;

import com.sg.eirp.common.dto.user.AddressDto;
import com.sg.eirp.common.mapper.base.DtoEntityMapper;
import com.sg.eirp.program.model.Address;
import org.springframework.beans.BeanUtils;

public class AddressMapper extends DtoEntityMapper<AddressDto, Address> {

    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return address;
    }

    @Override
    public AddressDto entityToDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);
        return addressDto;
    }
}
