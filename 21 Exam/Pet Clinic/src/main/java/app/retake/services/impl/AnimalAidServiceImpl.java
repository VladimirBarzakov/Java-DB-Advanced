package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.ModelParserImpl;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    AnimalAidRepository animalAidRepository;
    ModelParserImpl modelParser;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository,
                                ModelParserImpl modelParser) {
        this.animalAidRepository = animalAidRepository;
        this.modelParser=modelParser;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {

        AnimalAid animalAid = modelParser.convert(dto,AnimalAid.class);
        AnimalAid animalAidDB = null;
        List<AnimalAid> list = this.animalAidRepository.findByName(animalAid.getName());
        if (!list.isEmpty()){
            animalAidDB=list.get(0);
        }

        if (animalAidDB!=null && animalAidDB.getPrice().equals(animalAid.getPrice())){
            throw new IllegalArgumentException();
        }
        this.animalAidRepository.save(animalAid);


    }
}
