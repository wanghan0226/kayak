package service;

import beans.Ticket;
import org.dom4j.Document;

import java.util.List;

/**
 * @author pianobean on 3/12/15.
 */
public interface OneStopService {
    public List<Ticket> validOneStop(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay);
}
