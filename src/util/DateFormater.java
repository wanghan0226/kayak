package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pianobean on 3/12/15.
 */
public class DateFormater {
    public static Date format(String input){
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");
        Date date = null;
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static void main(String[] args) {
    }
}
