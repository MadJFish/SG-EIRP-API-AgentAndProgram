package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.user.AddressDto;

import java.util.UUID;

public interface AddressService {

    AddressDto save(AddressDto dto);

    AddressDto getByAddressId(UUID addressId);

}
