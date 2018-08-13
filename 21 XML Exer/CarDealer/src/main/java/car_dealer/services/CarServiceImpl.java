package car_dealer.services;

import car_dealer.entity.dto.binding.CarBindingModel;
import car_dealer.entity.dto.view.CarViewModel;
import car_dealer.entity.dto.view.carAndPartsView.PartViewModel;
import car_dealer.entity.dto.view.carAndPartsView.CarAndPartsViewModel;
import car_dealer.entity.models.Car;
import car_dealer.entity.models.Part;
import car_dealer.repositories.CarRepository;
import car_dealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class CarServiceImpl {

    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    public void saveDTO(List<CarBindingModel> carBindingModels) {
        List<Part> partList = this.partRepository.findAll();
        Random random = new Random();

        for (CarBindingModel carModel : carBindingModels) {
            Car car = new Car();
            car.setMake(carModel.getMake());
            car.setModel(carModel.getModel());
            car.setTravelledDistance(carModel.getTravelledDistance());
            int partsCount = 10+random.nextInt(11);
            BigDecimal carPrice = new BigDecimal(0d);
            Set<Part> carParts = new HashSet<>();
            while (carParts.size()<partsCount){
                Part part=partList.get(random.nextInt(partList.size()));
                carParts.add(part);
                carPrice=carPrice.add(part.getPrice());
            }
            car.setPrice(carPrice);
            this.carRepository.saveAndFlush(car);
            car = this.carRepository.getLastInserted().get(0);
            for (Part carPart : carParts) {
                carPart.getCars().add(car);
                this.partRepository.save(carPart);
            }
        }
    }

    public List<CarViewModel> getAllWithMake(String make) {
        return this.carRepository.getAllFromMake(make);
    }

    public List<CarAndPartsViewModel> getCarsAndParts() {
        List<Car> cars = this.carRepository.findAll();
        List<CarAndPartsViewModel> carAndPartsViewModelList = new ArrayList<>();
        for (Car car : cars) {
            List<PartViewModel> pars = this.partRepository.getAllByCar(car);

            CarAndPartsViewModel model = new CarAndPartsViewModel(
                    car.getMake(),
                    car.getModel(),
                    car.getTravelledDistance(),
                    pars);
            carAndPartsViewModelList.add(model);
        }

        return carAndPartsViewModelList;
    }
}
