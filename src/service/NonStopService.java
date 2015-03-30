package service;


import beans.Ticket;
import org.dom4j.Document;

import java.util.List;

/**
 * @author pianobean.
 */
public interface NonStopService {
    public List<Ticket> findNonStopFlights(String seatType, int numOfPassenger, String arCode, Document document);
}
