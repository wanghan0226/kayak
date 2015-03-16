package util;

import java.util.*;

/**
 * @author pianobean
 */
public class Pagination {
    private static final int SIZE = 10;
    public static Map distinguish(List nonStop, List oneStop,int pageNum){
       //首先将两个list合并准备查找
        ArrayList all = new ArrayList(nonStop);
        all.addAll(oneStop);
//        System.out.println(all);
        //获取分页参数
        int oneSize = oneStop.size();
        int nonSize = nonStop.size();
//        System.out.println((int)Math.ceil((double)17/10));
        int pages = (int)Math.ceil((double)(oneSize+nonSize)/SIZE);
        int divide = (int)Math.ceil((double)nonSize/SIZE);//根据直飞航班数量判断分割页
        int extra = nonSize%SIZE;//分割页的直飞航班数量
        Map map = new HashMap();
        List content = new ArrayList();
//        System.out.println(pageNum+"  "+divide+"  "+pages);
        if(pageNum==divide && divide<pages){//用户输入页码等于划分页,并且划分页小于尾页
            int divideStart = 10*(pageNum-1);//分割页第一个元素在nonStop里面的index位置
            if(extra==0){//nonstop的航班数量正好能被10整除
                for(int x=divideStart; x<nonStop.size();x++){
                    content.add(nonStop.get(x));
                }
                map.put("NON",content);
            }else {
                for (int i = divideStart; i < divideStart + extra; i++) {
                    content.add(nonStop.get(i));
                }
                for (int j = 0; j < SIZE - extra; j++) {
                    content.add(oneStop.get(j));
                }
                map.put(extra, content);
            }
        }
        else if(pageNum>divide && pageNum<pages){//用户输入页码大于划分页,但小于尾页
            if(extra!=0) {
                int startIndex = (pageNum - divide - 1) * SIZE + (SIZE - extra);//获取该页第一个元素的指针位置
                int endIndex = (pageNum - divide) * SIZE + (SIZE - extra);//该页最后一个元素的指针位置
                for (int i = startIndex; i < endIndex; i++) {
                    content.add(oneStop.get(i));
                }
            }else {
                int startIndex = (pageNum-divide-1)*SIZE;
                int endIndex = (pageNum-divide)*SIZE;
                for(int i=startIndex; i<endIndex; i++){
                    content.add(oneStop.get(i));
                }
            }
            map.put("ONE", content);

        }else if(pageNum==divide && divide==pages){//用户输入页码等于划分页,并且等于尾页
            System.out.println("here");
            int startIndex = nonStop.size()-extra;
            for(int i=startIndex; i<nonStop.size(); i++){
                content.add(nonStop.get(i));
            }
            for(int j=0; j<oneStop.size(); j++){
                content.add(oneStop.get(j));
            }
            map.put(extra, content);
        }else if(pageNum<divide){//用户输入页码小于划分页
            int startIndex = (pageNum-1)*10;
            for(int i=startIndex; i<startIndex+10; i++){
                content.add(nonStop.get(i));
            }
            map.put("NON",content);

        }else {//用户输入页码大于划分页且等于尾页

            if(extra!=0) {
                int startIndex = (pageNum - divide - 1) * SIZE + (SIZE - extra);
                for (int i = startIndex; i < oneStop.size(); i++) {
                    content.add(oneStop.get(i));
                }

            }else {
                int startIndex = (pageNum - divide - 1) * SIZE;
                for(int i=startIndex; i<oneStop.size(); i++){
                    content.add(oneStop.get(i));
                }
            }
            map.put("ONE", content);
        }
        return map;
    }
    public static void main(String[] args) {
        ArrayList a = new ArrayList();

        ArrayList b = new ArrayList();
        String s = "fdjksf";
        if(s instanceof String){
            System.out.println("good");
        }

    }
}
