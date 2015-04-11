package util;

import java.util.*;

/**
 * @author pianobean
 */
public class Pagination {
    public static final int SIZE = 10;
    public static List pageResult(List sortedList,int pageNum){
        int startIndex = (pageNum-1)*SIZE;
        int endIndex = pageNum*SIZE-1;
        List list = new ArrayList(SIZE);
        for(int i=startIndex; i<=endIndex && i<sortedList.size(); i++){
            list.add(sortedList.get(i));
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(String.class.getName());

    }
}
