package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.*;
import app.retake.parser.ModelParserImpl;
import app.retake.repositories.*;
import app.retake.services.api.ProcedureService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.MappedByteBuffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureRepository procedureRepository;
    private VetRepository vetRepository;
    private PassportRepository passportRepository;
    private AnimalAidRepository animalAidRepository;

    private ModelParserImpl modelParser;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository,
                                VetRepository vetRepository,
                                PassportRepository passportRepository,
                                AnimalAidRepository animalAidRepository,
                                ModelParserImpl modelParser) {
        this.procedureRepository = procedureRepository;
        this.vetRepository = vetRepository;
        this.passportRepository = passportRepository;
        this.animalAidRepository = animalAidRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {

        Procedure procedure = new Procedure();
        String vetName = dto.getVet();
        Vet vet = this.vetRepository.getOneByName(vetName);
        String animalPassport = dto.getAnimal();
        Passport passport = this.passportRepository.findOneBySerialNumber(animalPassport);
        Animal animal = passport.getAnimal();
        List<String> animalAidsNames = dto.getAnimalAids()
                .stream()
                .map(ProcedureAnimalAidXMLImportDTO::getName)
                .collect(Collectors.toList());

        if (vet==null && animal==null){
            throw  new IllegalArgumentException();
        }

        List<AnimalAid> animalAids = new ArrayList<>();
        for (String animalAidsName : animalAidsNames) {
            AnimalAid animalAid = null;
            List<AnimalAid> list = this.animalAidRepository.findByName(animalAidsName);
            if (!list.isEmpty()){
                animalAid=list.get(0);
            }
            if (animalAid==null){
                throw  new IllegalArgumentException();
            }
            animalAids.add(animalAid);
        }

        procedure.setVet(vet);
        procedure.setAnimal(animal);
        procedure.setAnimalAids(animalAids);


        procedure = this.procedureRepository.save(procedure);
        for (AnimalAid animalAid : animalAids) {
            animalAid.getProcedures().add(procedure);
        }
        this.animalAidRepository.save(animalAids);

    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {

        List<Procedure> procedures = this.procedureRepository.findAll();
        List<ProcedureXMLExportDTO> procedureDTOs = new ArrayList<>();
        for (Procedure procedure : procedures) {
            ProcedureXMLExportDTO prDTO = new ProcedureXMLExportDTO();
            Animal animal=procedure.getAnimal();
            Passport passport=animal.getPassport();
            prDTO.setOwnerPhone(passport.getOwnerPhoneNumber());
            prDTO.setAnimalPassport(passport.getSerialNumber());

            List<ProcedureAnimalAidXMLExportDTO> aidsDTO = new ArrayList<>();
            List<AnimalAid> animalAids = procedure.getAnimalAids();
            for (AnimalAid animalAid : animalAids) {
                ProcedureAnimalAidXMLExportDTO aidDTO = new ProcedureAnimalAidXMLExportDTO();
                aidDTO.setName(animalAid.getName());
                aidsDTO.add(aidDTO);
            }
            prDTO.setAnimalAids(aidsDTO);
            procedureDTOs.add(prDTO);
        }

        ProcedureWrapperXMLExportDTO wrapper = new ProcedureWrapperXMLExportDTO();
        wrapper.setProcedures(procedureDTOs);


        return wrapper;
    }
}

