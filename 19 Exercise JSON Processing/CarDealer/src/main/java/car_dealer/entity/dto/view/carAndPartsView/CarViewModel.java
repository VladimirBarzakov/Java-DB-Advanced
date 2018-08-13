package car_dealer.entity.dto.view.carAndPartsView;

import com.google.gson.annotations.Expose;

public class CarViewModel {
    @Expose
    private String Make;

    @Expose
    private String Model;

    @Expose
    private long TravelledDistance;

    public CarViewModel(String make, String model, long travelledDistance) {
        Make = make;
        Model = model;
        TravelledDistance = travelledDistance;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
