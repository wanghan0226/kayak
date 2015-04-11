package serviceImpl;

import beans.Flight;
import beans.SortingBean;
import service.PairFlights;

import java.util.*;

/**
 * Created by pianobean on 3/13/15.
 */
public class PairFlightsImpl implements PairFlights{
    public List<List<List<Flight>>> pairOneStop(List<List<Flight>> goFlights, List<List<Flight>> comeFlights){
        List list = new ArrayList();
        Iterator it = goFlights.iterator();
        while (it.hasNext()){
            List go = (List) it.next();
            for(int i=0; i<comeFlights.size(); i++){
                List pair = new ArrayList();
                List come = comeFlights.get(i);
                pair.add(go);
                pair.add(come);
                list.add(pair);
            }
        }
        return list;
    }

    @Override
    public Map<SortingBean, List<List<Flight>>> pairOneStop1(Map<SortingBean, List<Flight>> goFlights, Map<SortingBean, List<Flight>> comeFlights) {
        Map<SortingBean, List<List<Flight>>> map = new HashMap<SortingBean, List<List<Flight>>>();

        for(Map.Entry<SortingBean, List<Flight>> entryGo: goFlights.entrySet()){

            for(Map.Entry<SortingBean, List<Flight>> entryCome: comeFlights.entrySet()){
                SortingBean goKey = entryGo.getKey();
                SortingBean comeKey = entryCome.getKey();
                int totalTime = goKey.getTotalTime()+comeKey.getTotalTime();
                double totalPrice = goKey.getTotalPrice()+comeKey.getTotalPrice();
                SortingBean sort = new SortingBean();
                sort.setTotalPrice(totalPrice);
                sort.setTotalTime(totalTime);
                List<List<Flight>> list = new ArrayList();
                list.add(entryGo.getValue());
                list.add(entryCome.getValue());
                map.put(sort, list);
            }

        }

        return map;
    }

    public List<List<Flight>> pairNonStop(List<Flight> goFlights, List<Flight> comeFlights){
        List list = new ArrayList();
        Iterator it = goFlights.iterator();
        while (it.hasNext()){
            Flight go = (Flight) it.next();
            for(int i=0; i<comeFlights.size(); i++){
                List pair = new ArrayList();
                Flight come = comeFlights.get(i);
                pair.add(go);
                pair.add(come);
                list.add(pair);
            }
        }
        return list;
    }

    @Override
    public Map<SortingBean, List<Flight>> pairNonStop1(Map<SortingBean, Flight> goFlights, Map<SortingBean, Flight> comeFlights) {

        Map<SortingBean, List<Flight>> map = new HashMap<SortingBean, List<Flight>>();

        for(Map.Entry<SortingBean, Flight> entryGo: goFlights.entrySet()){

            for(Map.Entry<SortingBean, Flight> entryCome: comeFlights.entrySet()){
                SortingBean goKey = entryGo.getKey();
                SortingBean comeKey = entryCome.getKey();
                int totalTime = goKey.getTotalTime()+comeKey.getTotalTime();
                double totalPrice = goKey.getTotalPrice()+comeKey.getTotalPrice();
                SortingBean sort = new SortingBean();
                sort.setTotalPrice(totalPrice);
                sort.setTotalTime(totalTime);
                List<Flight> list = new ArrayList();
                list.add(entryGo.getValue());
                list.add(entryCome.getValue());
                map.put(sort, list);
            }

        }

        return map;
    }
}
