package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  pianobean
 * This class help to format the input date to given format.
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
