package beans;

/**
 * Created by pianobean on 3/9/15.
 */
public class AirPlane {
    private String manufacturer;
    private String model;
    private int firstSeat;
    private int coachSeat;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFirstSeat() {
        return firstSeat;
    }

    public void setFirstSeat(int firstSeat) {
        this.firstSeat = firstSeat;
    }

    public int getCoachSeat() {
        return coachSeat;
    }

    public void setCoachSeat(int coachSeat) {
        this.coachSeat = coachSeat;
    }
}
