package beans;

/**
 * Created by pianobean on 3/27/15.
 */
public class ReserveInfo {
    private String flightNum;
    private String seat;

    public ReserveInfo(String flightNum, String seat){
        this.flightNum = flightNum;
        this.seat = seat;
    }
    public ReserveInfo(){}

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
