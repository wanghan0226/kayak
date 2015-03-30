package service;

import beans.Flight;
import beans.Ticket;
import beans.TicketContent;

import java.util.List;

/**
 * @author pianobean on 3/13/15.
 */
public interface PairFlights {
//    public List<Ticket> pairOneStop(List<Ticket> goFlights, List<Ticket> comeFlights);
//    public List<Ticket> pairNonStop(List<Ticket> goFlights, List<Ticket> comeFlights);
    public List<Ticket> pairFlight(List<Ticket> goFlights, List<Ticket> comeFlights);
}
