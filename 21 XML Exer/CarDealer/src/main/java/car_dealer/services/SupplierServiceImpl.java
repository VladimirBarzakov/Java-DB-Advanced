package car_dealer.services;

import car_dealer.entity.dto.binding.SupplierBindingModel;
import car_dealer.entity.dto.view.SupplierViewModel;
import car_dealer.entity.models.Supplier;
import car_dealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void saveDTO(List<SupplierBindingModel> supplierBindingModels) {
        List<Supplier> suppliers = new ArrayList<>();
        for (SupplierBindingModel model : supplierBindingModels) {
            Supplier supplier = new Supplier();
            supplier.setImporter(model.isImporter());
            supplier.setName(model.getName());
            suppliers.add(supplier);
        }
        this.supplierRepository.saveAll(suppliers);
    }

    public List<SupplierViewModel> getAllLocals() {

        return this.supplierRepository.getAllLocals();

    }
}
