package beans;

import javafx.util.converter.TimeStringConverter;

import java.util.*;

/**
 * @author Yun Lu
 * <p>
 * This class is the Ticket class with has the ticket infomation
 * including:
 * <ol>
 * <li>Round Trip/Single Trip</>
 * <li>One-stop, Non-stop</li>
 * <li>And flight info</li>
 * </ol>
 * </p>
 */
public class Ticket {
    private float price;
    private long duration;
    private FLIGHT_TYPE flightType;
    List<TicketContent> ticketContents; /* For example: <Round, <one-stop, <Flight1, Flight2>....>>*/



    /**
     * This function the constructor for a ticket
     * @param
     */
    public Ticket(FLIGHT_TYPE flightType,  List<TicketContent> ticketContent, float price, long duration){
        this.ticketContents = ticketContent;
        this.flightType = flightType;
        this.price = price;
//        this.arrivalTime = arrivalTime;
//        this.departureTime = departureTime;
        this.duration = duration;
    }
    public String getFlightType(){
        return flightType.toString();
    }

    public List<TicketContent> getTicketContents(){
        return this.ticketContents;
    }
    public float getPrice(){
        return price;
    }

    public long getDuration(){
        return duration;
    }



}
