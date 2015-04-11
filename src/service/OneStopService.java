package service;

import beans.Flight;
import beans.SortingBean;
import org.dom4j.Document;

import java.util.List;
import java.util.Map;

/**
 * @author pianobean on 3/12/15.
 */
public interface OneStopService {
    public List<List<Flight>> validOneStop(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay);
    public Map<SortingBean, List<Flight>> validOneStop1(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay);
}
