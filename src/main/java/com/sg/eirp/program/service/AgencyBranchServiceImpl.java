package com.sg.eirp.program.service;

import com.sg.eirp.common.dto.agency.TutorAgencyBranchDto;
import com.sg.eirp.common.dto.user.AddressDto;
import com.sg.eirp.program.mapper.AgencyBranchMapper;
import com.sg.eirp.program.model.AgencyBranch;
import com.sg.eirp.program.repo.AgencyBranchRepo;
import com.sg.eirp.program.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional(rollbackFor = Exception.class)
@Service
public class AgencyBranchServiceImpl implements AgencyBranchService {

    private static Logger logger = LoggerFactory.getLogger(AgencyBranchServiceImpl.class);

    @Autowired
    private AgencyBranchRepo agencyBranchRepo;

    @Autowired
    private AgencyBranchMapper agencyBranchMapper;

    @Autowired
    private AddressService addressService;

    @Override
    public TutorAgencyBranchDto save(TutorAgencyBranchDto dto) {
        if (dto == null) {
            return null;
        }

        // save address
        AddressDto addressDto = dto.getAddress();
        if (addressDto != null) {
            addressDto = addressService.save(addressDto);
        }

        logger.info("save address: " + addressDto.toString());

        // save agency branch
        AgencyBranch agencyBranch = agencyBranchMapper.dtoToEntity(dto);
        if (agencyBranch != null) {
            if (addressDto != null && addressDto.getId() != null) {
                logger.info("address id: " + addressDto.getId());
                agencyBranch.setAddressId(CommonUtil.convertIdtoUUID(addressDto.getId()));
            }
            agencyBranch = agencyBranchRepo.save(agencyBranch);
        }

        // convert entity to dto
        TutorAgencyBranchDto tutorAgencyBranchDto = agencyBranchMapper.entityToDto(agencyBranch);
        if (tutorAgencyBranchDto != null) {
            tutorAgencyBranchDto.setAddress(addressDto);
        }

        return tutorAgencyBranchDto;
    }

    @Override
    public Optional<List<TutorAgencyBranchDto>> saveAll(List<TutorAgencyBranchDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }

        return Optional.ofNullable(dtoList.stream()
                .map(dto -> save(dto))
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<TutorAgencyBranchDto>> getBranchesByAgencyId(UUID agencyId) {
        if (agencyId == null) {
            return null;
        }

        Optional<List<AgencyBranch>> agencyBranchListOptional = agencyBranchRepo.getByAgencyId(agencyId);
        List<TutorAgencyBranchDto> tutorAgencyBranchDtoList = null;
        if (agencyBranchListOptional.isPresent()) {
            tutorAgencyBranchDtoList = agencyBranchListOptional.get()
                                        .stream()
                                        .map(agencyBranch -> {
                                            // convert entity to dto
                                            TutorAgencyBranchDto tutorAgencyBranchDto = agencyBranchMapper.entityToDto(agencyBranch);

                                            // find address through address id
                                            if (tutorAgencyBranchDto != null) {
                                                UUID addressId = agencyBranch.getAddressId();
                                                AddressDto addressDto = addressService.getByAddressId(addressId);
                                                tutorAgencyBranchDto.setAddress(addressDto);
                                            }

                                            return tutorAgencyBranchDto;
                                        })
                                        .collect(Collectors.toList());
        }

        return Optional.ofNullable(tutorAgencyBranchDtoList);
    }

    @Override
    public Optional<List<TutorAgencyBranchDto>> getAll() {
        Iterable<AgencyBranch> agencyBranchIterable = agencyBranchRepo.findAll();

        // convert the iterable to list
        List<AgencyBranch> branchList = StreamSupport.stream(agencyBranchIterable.spliterator(), false)
                .collect(Collectors.toList());

        return agencyBranchMapper.entitiesToDtos(Optional.ofNullable(branchList));
    }
}
