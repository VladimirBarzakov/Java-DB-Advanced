package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.entity.District;
import org.softuni.mostwanted.domain.entity.Race;
import org.softuni.mostwanted.domain.entity.RaceEntry;
import org.softuni.mostwanted.domain.JSON.XML.EntryXmlImport;
import org.softuni.mostwanted.domain.JSON.XML.RaceXMLImport;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RaceServiceImpl {

    @Autowired
    private ModelParserImpl modelParser;


    @Autowired
    private RaceEntryRepository raceEntryRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private DistrictRepository districtRepository;


    public int create(RaceXMLImport dto) {

        Race race = this.modelParser.convert(dto,Race.class);

        //Different towns can have districts with same names, district name is not enough to identify districts

        List<District> districts = this.districtRepository.findOneByName(dto.getDistrictName());
        District district = null;
        if (districts==null){
            throw  new IllegalArgumentException();
        }
        district=districts.get(0);

        List<EntryXmlImport> entryImports = dto.getEntrees();
        Set<RaceEntry> raceEntries = new HashSet<>();

        for (EntryXmlImport entryImport : entryImports) {
            RaceEntry raceEntry = this.raceEntryRepository.findOneById(entryImport.getId());
            if (raceEntries==null){
                throw  new IllegalArgumentException();
            }

            raceEntries.add(raceEntry);
        }

        race.setDistrict(district);
        race.setEntries(raceEntries);
        race=this.raceRepository.save(race);

        return race.getId();

    }
}
