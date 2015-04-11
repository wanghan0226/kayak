package factory;

import service.*;
import serviceImpl.*;

/**
 *@author pianobean on 3/12/15.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance;
    }
    public AirportService getAirportService(){
        return new AirportServiceImpl();
    }
    public OneStopService getOneStopService(){
        return new OneStopServiceImpl();
    }
    public NonStopService getNonStopService(){
        return new NonStopServiceImpl();
    }
    public PairFlights getPairFlights(){
        return new PairFlightsImpl();
    }
    public SortFlights getSortedFlights(){ return new SortFlightsImpl(); }
}
