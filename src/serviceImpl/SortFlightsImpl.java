package serviceImpl;

import beans.SortingBean;
import service.SortFlights;

import java.util.*;

/**
 * Created by pianobean on 4/9/15.
 */
public class SortFlightsImpl implements SortFlights {
    @Override
    public List sortByPrice(List showInfo) {
        showInfo.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                 Map.Entry set1 = (Map.Entry) o1;
                 Map.Entry set2 = (Map.Entry) o2;
                 SortingBean key1 = (SortingBean) set1.getKey();
                 SortingBean key2 = (SortingBean) set2.getKey();
                return (int) (key1.getTotalPrice()-key2.getTotalPrice());
            }
        });

        return showInfo;
    }

    @Override
    public List sortByPriceDec(List showInfo) {
        showInfo.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map.Entry set1 = (Map.Entry) o1;
                Map.Entry set2 = (Map.Entry) o2;
                SortingBean key1 = (SortingBean) set1.getKey();
                SortingBean key2 = (SortingBean) set2.getKey();
                return (int) (key2.getTotalPrice()-key1.getTotalPrice());
            }
        });

        return showInfo;
    }

    @Override
    public List sortByTime(List showInfo) {
        showInfo.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map.Entry set1 = (Map.Entry) o1;
                Map.Entry set2 = (Map.Entry) o2;
                SortingBean key1 = (SortingBean) set1.getKey();
                SortingBean key2 = (SortingBean) set2.getKey();
                return key1.getTotalTime()-key2.getTotalTime();
            }
        });

        return showInfo;
    }

    @Override
    public List sortByTimeDec(List showInfo) {
        showInfo.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map.Entry set1 = (Map.Entry) o1;
                Map.Entry set2 = (Map.Entry) o2;
                SortingBean key1 = (SortingBean) set1.getKey();
                SortingBean key2 = (SortingBean) set2.getKey();
                return key2.getTotalTime()-key1.getTotalTime();
            }
        });

        return showInfo;
    }
}
