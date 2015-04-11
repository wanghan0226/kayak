package beans;

import util.Pagination;

import java.util.List;

/**
 * Created by pianobean on 4/11/15.
 */
public class PageBean {
    private int totalPage;
    private int totalRecord;
    private int currentPage;
    private int prePage;
    private int nextPage;
    private int[] pageBar;


    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        if(totalRecord% Pagination.SIZE==0){
            this.totalPage = totalRecord/Pagination.SIZE;
        }else {
            this.totalPage = totalRecord/Pagination.SIZE+1;
        }

        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPrePage() {
        if(this.currentPage-1<1) this.prePage = 1;
        else this.prePage = this.currentPage-1;
        return prePage;
    }


    public int getNextPage() {
        if(this.currentPage+1>=this.totalPage) this.nextPage = this.totalPage;
        else this.nextPage = currentPage+1;
        return nextPage;
    }


    public int[] getPageBar() {
        int startIndex;
        int endIndex;
        int[] pages;
        if(this.getTotalPage()<=10){
//            System.out.println("good");
            pages = new int[this.getTotalPage()];
            startIndex =1;
            endIndex = this.getTotalPage();
        }else {
            pages = new int[10];
            startIndex =this.currentPage-4;
            endIndex = this.currentPage+5;

            if(startIndex<1){
                startIndex = 1;
                endIndex = 10;
            }

            if(endIndex>this.getTotalPage()){
                startIndex = this.getTotalPage()-9;
                endIndex = this.getTotalPage();
            }
        }
        int x = 0;
        for(int i=startIndex; i<=endIndex; i++){
            pages[x++] = i;
        }
        return pages;
    }

}
