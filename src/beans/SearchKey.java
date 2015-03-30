package beans;

import java.util.Date;

/**
 * @author pianobean
 */
public class SearchKey {
    private String number;
    private String airportCode;
    private Date date;
    private int firstSeat;
    private int coachSeat;
    private float firstPrice;
    private float coachPrice;
    private long departureTime;
    private long arrivalTime;

    public float getFirstPrice(){return firstPrice;}

    public void setFirstPrice(float price){this.firstPrice = price;}

    public float getCoachPrice(){return  coachPrice;}

    public void setCoachPrice(float price){this.coachPrice = price;}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public void setDepartureTime(long departureTime){ this.departureTime = departureTime; }

    public long getDepartureTime(){ return departureTime; }

    public void setArrivalTime(long arrivalTime){ this.arrivalTime = arrivalTime; }

    public long getArrivalTime(){ return arrivalTime; }
}
