package service;

import beans.Flight;
import beans.SortingBean;
import org.dom4j.Document;

import java.util.List;
import java.util.Map;

/**
 * @author pianobean.
 */
public interface NonStopService {
    public List<Flight> findNonStopFlights(String seatType, int numOfPassenger, String arCode, Document document);
    public Map<SortingBean, Flight> findNonStopFlights1(String seatType, int numOfPassenger, String arCode, Document document);
}
