package factory;

import dao.AirportFunctions;
import daoImpl.AirportInfo;

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

    public AirportFunctions getAirportFunctions(){
        return new AirportInfo();
    }
}
