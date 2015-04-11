package service;

import java.util.List;

/**
 * Created by pianobean on 4/9/15.
 */
public interface SortFlights {
    public List sortByPrice(List showInfo);
    public List sortByPriceDec(List showInfo);
    public List sortByTime(List showInfo);
    public List sortByTimeDec(List showInfo);
}
