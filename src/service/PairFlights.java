package service;

import beans.Flight;
import beans.SortingBean;

import java.util.List;
import java.util.Map;

/**
 * Created by pianobean on 3/13/15.
 */
public interface PairFlights {
    public List<List<List<Flight>>> pairOneStop(List<List<Flight>> goFlights, List<List<Flight>> comeFlights);
    public Map<SortingBean, List<List<Flight>>> pairOneStop1(Map<SortingBean, List<Flight>> goFlights, Map<SortingBean, List<Flight>> comeFlights);
    public List<List<Flight>> pairNonStop(List<Flight> goFlights, List<Flight> comeFlights);
    public Map<SortingBean, List<Flight>> pairNonStop1(Map<SortingBean, Flight> goFlights, Map<SortingBean, Flight> comeFlights);
}
