package service;

import beans.Flight;

import java.util.List;

/**
 * Created by pianobean on 3/13/15.
 */
public interface PairFlights {
    public List<List<List<Flight>>> pairOneStop(List<List<Flight>> goFlights, List<List<Flight>> comeFlights);
    public List<List<Flight>> pairNonStop(List<Flight> goFlights, List<Flight> comeFlights);
}
