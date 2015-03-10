package beans;

import java.util.Date;

/**
 * Created by pianobean on 3/9/15.
 */
public class Flight {
    private String planeType;
    private int flightTime;
    private String number;
    private String departCode;
    private Date departTime;
    private String arriveCode;
    private Date arriveTime;
    private int firstLeft;
    private int coachLeft;
    private double firstPrice;
    private double coachPrice;

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public String getArriveCode() {
        return arriveCode;
    }

    public void setArriveCode(String arriveCode) {
        this.arriveCode = arriveCode;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getFirstLeft() {
        return firstLeft;
    }

    public void setFirstLeft(int firstLeft) {
        this.firstLeft = firstLeft;
    }

    public int getCoachLeft() {
        return coachLeft;
    }

    public void setCoachLeft(int coachLeft) {
        this.coachLeft = coachLeft;
    }

    public double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public double getCoachPrice() {
        return coachPrice;
    }

    public void setCoachPrice(double coachPrice) {
        this.coachPrice = coachPrice;
    }

    @Override
    public String toString() {
        return number+":"+planeType+":"+departCode+":"+arriveCode;
    }
}
