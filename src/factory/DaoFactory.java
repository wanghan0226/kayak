package factory;

import dao.AirportDao;
import dao.ConnectDao;
import daoImpl.AirportDaoImpl;
import daoImpl.ConnectDaoImpl;

/**
 * Created by pianobean on 3/9/15.
 */
public class DaoFactory {
    //singleton design pattern
    private static final DaoFactory instance = new DaoFactory();
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance;
    }

    public AirportDao getAirportFunctions(){
        return new AirportDaoImpl();
    }

    public ConnectDao getConnectFlights(){
        return new ConnectDaoImpl();
    }
}
