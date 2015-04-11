package util;

import beans.ReserveInfo;

import java.util.*;

/**
 * Created by pianobean on 3/27/15.
 */
public class ReserveParams {
    public static String reservationCreator(List<ReserveInfo> list){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++) {
            ReserveInfo info = list.get(i);
            sb.append("<Flight number=\"" + info.getFlightNum() + "\" seating=\"" + info.getSeat() + "\"/>");
        }
        String result = "<Flights>"+sb.toString()+"</Flights>";
        return result;
    }

    public static void main(String[] args) {
          ReserveInfo info = new ReserveInfo("1913","FirstClass");
          ReserveInfo info1 = new ReserveInfo("1913","Coach");

        List list = new ArrayList();
        list.add(info);
        list.add(info1);
        String s = ReserveParams.reservationCreator(list);
        System.out.println(s);

    }

}
