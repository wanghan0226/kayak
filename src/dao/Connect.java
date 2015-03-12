package dao;

import beans.SearchKey;
import org.dom4j.Document;

import java.util.List;

/**
 * Created by pianobean on 3/11/15.
 */
public interface Connect {
    public List<SearchKey> airportSearchInfo(Document document, String airportType);
}
