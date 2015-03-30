package beans;

import java.util.List;
import java.util.Map;

/**
 * Created by Yun Lu on 3/18/2015.
 */
public class TicketContent {
    STOP_NUM stopNum;
    List<Flight> flights;

    public TicketContent(STOP_NUM stopNum, List<Flight> flights){
        this.stopNum = stopNum;
        this.flights = flights;
    }

    public List<Flight> getFligts(){
        return flights;
    }
}
