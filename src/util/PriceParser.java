package util;

/**
 * Created by Yun Lu on 3/26/2015.
 */
public class PriceParser {

    public static float floatParser(String input){
        if(input.contains("$")){
            input = input.substring(1);
        }
        if(input.contains(",")){
            int i = input.indexOf(",");
            input = input.substring(0,i) + input.substring(i+1);
        }
        return Float.parseFloat(input);
    }

    public static void main(String[] args){
        String input = "$1,234";
        System.out.println(PriceParser.floatParser(input));
    }
}
