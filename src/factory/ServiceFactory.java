package factory;

import service.AirportService;
import service.OneStopService;
import serviceImpl.AirportServiceImpl;
import serviceImpl.OneStopServiceImpl;

/**
 * Created by pianobean on 3/12/15.
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
}
