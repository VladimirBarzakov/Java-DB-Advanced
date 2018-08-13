package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.entity.Town;
import org.softuni.mostwanted.domain.export.JSON.TownJSONExportDTO;
import org.softuni.mostwanted.domain.JSON.JSON.TownDTOImportJSON;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl {


    @Autowired
    private ModelParserImpl modelParser;

    @Autowired
    private TownRepository townRepository;

    public TownServiceImpl() {
    }


    public void create(TownDTOImportJSON townDTOImportJSON) {

        Town town = this.townRepository.findOneByName(townDTOImportJSON.getName());
        if (town!=null){
            throw  new IllegalArgumentException();
        }
        town = this.modelParser.convert(townDTOImportJSON, Town.class);
        this.townRepository.save(town);
    }


    public List<TownJSONExportDTO> getAllTownsWithRacers() {
        List<TownJSONExportDTO> set = this.townRepository.getAllTownsWithRacers();
        List<TownJSONExportDTO> result=new ArrayList<>();
        for (TownJSONExportDTO townExportDTO : set) {
            if (townExportDTO.getRacers()!=null && townExportDTO.getRacers()!=0){
                result.add(townExportDTO);
            }
        }

        String debug="";

        return result;

    }
}
