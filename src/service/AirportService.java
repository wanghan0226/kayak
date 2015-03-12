package service;

import org.dom4j.Document;

import java.util.Date;

/**
 * Created by pianobean on 3/12/15.
 */
public interface AirportService {
    public String generateName(String keyWords);
    public Document getDepartDom(String code, Date date);
    public Document getArriveDom(String code, Date date);
}
