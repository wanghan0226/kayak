package service;

import beans.Flight;
import org.dom4j.Document;

import java.util.List;

/**
 * @author pianobean on 3/12/15.
 */
public interface OneStopService {
    public List<List<Flight>> validOneStop(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay);
}
