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
}
