package app.service.api;

import app.model.dto.accessories.AccessoryXMLDto;
import app.model.entities.Accessory;

import java.util.List;

public interface AccessoriesService {
    void create(Accessory accessory);
    void createMany(AccessoryXMLDto[] dtos);
}
