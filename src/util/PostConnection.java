package util;

import beans.ReserveInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pianobean on 3/27/15.
 */
public class PostConnection {

    private static final String pUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

    public static boolean DBOperate(String operation){
        URL url;
        HttpURLConnection connection;
        try{
            url = new URL(pUrlBase);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(operation);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();
            System.out.println("response code:" + responseCode);

            if((responseCode>=200) && (responseCode<=299)){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = in.readLine())!=null){
                    response.append(line);
                }
                in.close();
                System.out.println(response.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ReserveInfo info = new ReserveInfo("1913","FirstClass");
        ReserveInfo info1 = new ReserveInfo("1913","Coach");

        List list = new ArrayList();
        list.add(info1);
        list.add(info);

        String reserve = ReserveParams.reservationCreator(list);
//        System.out.println(PostConnection.lockDB());
        String param = QueryFactory.reserve(reserve);
//        System.out.println(reserve);
        PostConnection.DBOperate(QueryFactory.lock());
        PostConnection.DBOperate(param);
        PostConnection.DBOperate(QueryFactory.unlock());
    }
}
