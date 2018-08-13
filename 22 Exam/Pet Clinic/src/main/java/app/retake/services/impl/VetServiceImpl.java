package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;
import app.retake.parser.ModelParserImpl;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private ModelParserImpl modelParser;

    @Override
    public void create(VetXMLImportDTO dto) {

        Vet vet = this.modelParser.convert(dto, Vet.class);
        this.vetRepository.save(vet);
    }
}
