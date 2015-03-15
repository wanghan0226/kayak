package dao;

import beans.Flight;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pianobean
 */
public interface AirportDao {
    public Map<String, String> findCodesAndNames();
    public List<String> findDepartNumbers(String code, Date departDate);
    public List<String> findArriveNumbers(String code, Date departDate);
    public Flight findFlightByNumber(String num, Document document);

}
