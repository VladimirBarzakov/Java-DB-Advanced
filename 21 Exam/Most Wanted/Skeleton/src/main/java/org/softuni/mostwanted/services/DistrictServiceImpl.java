package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.entity.District;
import org.softuni.mostwanted.domain.entity.Town;
import org.softuni.mostwanted.domain.JSON.JSON.DistrictDTOImportJSON;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private TownRepository townRepository;


    public void create(DistrictDTOImportJSON dto) {

        District district = new District();
        Town town = this.townRepository.findOneByName(dto.getTownName());
        District chekDistrict = this.districtRepository.findOneByNameAndTown(dto.getName(),town);
        if (town==null&&dto.getTownName()!=null && chekDistrict!=null){
            throw  new IllegalArgumentException();
        }
        district.setName(dto.getName());
        district.setTown(town);

        this.districtRepository.save(district);
    }
}
