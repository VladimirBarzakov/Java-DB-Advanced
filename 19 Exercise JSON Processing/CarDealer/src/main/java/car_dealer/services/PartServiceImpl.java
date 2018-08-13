package car_dealer.services;

import car_dealer.entity.dto.binding.PartsBindingModel;
import car_dealer.entity.models.Part;
import car_dealer.entity.models.Supplier;
import car_dealer.repositories.PartRepository;
import car_dealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;


    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }

    public void saveDTO(List<PartsBindingModel> partsBindingModels) {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<Part> list = new ArrayList<>();
        Random random = new Random();
        for (PartsBindingModel partDTO : partsBindingModels) {
            Part part = new Part();
            part.setName(partDTO.getName());
            part.setPrice(partDTO.getPrice());
            part.setQuantity(partDTO.getQuantity());
            Supplier supplier = suppliers.get(random.nextInt(suppliers.size()));
            part.setSupplier(supplier);
            list.add(part);
        }
        this.partRepository.saveAll(list);
    }
}
