package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.user.AddressDto;
import com.sg.eirp.common.exceptions.BusinessValidationException;
import com.sg.eirp.program.mapper.AddressMapper;
import com.sg.eirp.program.model.Address;
import com.sg.eirp.program.repo.AddressRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(rollbackFor = Exception.class)
@Service
public class AddressServiceImpl implements AddressService {

    private static Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDto save(AddressDto dto) {
        if (dto == null) {
            return null;
        }

        // Convert Address dto to Address object
        Address address = addressMapper.dtoToEntity(dto);
        if (address == null) {
            logger.debug("Address dto cannot convert to Address object.");
            throw new BusinessValidationException();
        }

        address = addressRepo.save(address);

        logger.info("####### address Id: " + address.getId().toString());

        // Convert Address object to Address dto
        return addressMapper.entityToDto(address);
    }

    @Override
    public AddressDto getByAddressId(UUID addressId) {
        if (addressId == null) {
            return null;
        }

        Optional<Address> addressOptional = addressRepo.findById(addressId);
        return addressMapper.entityToDto(addressOptional.get());
    }
}
