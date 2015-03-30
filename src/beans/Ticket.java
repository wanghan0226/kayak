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
    private long time;
    private FLIGHT_TYPE flight_type;
    List<TicketContent> ticketContents; /* For example: <Round, <one-stop, <Flight1, Flight2>....>>*/


    /**
     * This function the constructor for a ticket
     * @param
     */
    public Ticket(FLIGHT_TYPE flightType,  List<TicketContent> ticketContent, float price, long time){
        this.ticketContents = ticketContent;
        this.flight_type = flightType;
        this.price = price;
        this.time = time;
    }
    public FLIGHT_TYPE getFlightType(){
        return flight_type;
    }

    public List<TicketContent> getTicketContents(){
        return this.ticketContents;
    }
    public float getPrice(){
        return price;
    }

    public long getTimeInterval(){
        return time;
    }

}
