package dao;

import beans.SearchKey;
import org.dom4j.Document;

import java.util.List;

/**
 * @author pianobean on 3/11/15.
 */
public interface ConnectDao {
    public List<SearchKey> airportSearchInfo(Document document, String airportType);
}
