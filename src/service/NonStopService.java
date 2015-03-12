package service;

import beans.Flight;
import org.dom4j.Document;

import java.util.List;

/**
 * Created by pianobean on 3/12/15.
 */
public interface NonStopService {
    public List<Flight> findNonStopFlights(String seatType, int numOfPassenger, String arCode, Document document);
}
