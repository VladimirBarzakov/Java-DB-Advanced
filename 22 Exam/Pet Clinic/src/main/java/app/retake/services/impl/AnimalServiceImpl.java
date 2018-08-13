package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.ModelParserImpl;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;
    private ModelParserImpl modelParser;
    private PassportRepository passportRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository,
                             PassportRepository passportRepository,
                             ModelParserImpl modelParser) {
        this.animalRepository = animalRepository;
        this.passportRepository=passportRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {

        Animal animal = this.modelParser.convert(dto, Animal.class);
        Passport passport = this.modelParser.convert(dto.getPassport(), Passport.class);
        try {
            this.passportRepository.save(passport);
            passport = this.passportRepository.findOneBySerialNumber(passport.getSerialNumber());
            animal.setPassport(passport);
            this.animalRepository.save(animal);
        } catch (Exception e){
            throw  new IllegalArgumentException();
        }

    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return this.animalRepository.getAllAnimalsByPhoneNumber(phoneNumber);
    }
}
