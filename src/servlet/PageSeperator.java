package servlet;

import util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pianobean on 3/13/15.
 */
@WebServlet(name = "PageSeperator")
public class PageSeperator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //获取类型 往返 或者 单程
        String type = (String) request.getAttribute("type");
        String pageNumberStr = (String) request.getAttribute("pageNumber");
        int pageNumber = 1;
        if(pageNumberStr!=null && !pageNumberStr.isEmpty())
        {
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        if(type.equals("oneWay")){//如果是单程
            //首先获取直飞和转机的航班
            List oneStop = (List) request.getAttribute("goOneStop");
            List nonStop = (List) request.getAttribute("goNonStop");
            Map showInfo = Pagination.distinguish(nonStop,oneStop,pageNumber);
        }else {

        }


//
//        int pageSize = 10; //分页大小
//        int totalPosts = PagingDAO.entryList.size(); //总文章数
//        int totalPages = totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //计算得出的总页数
//
//        request.setAttribute("pageSize", pageSize);
//        request.setAttribute("totalPosts", totalPosts);
//        request.setAttribute("pageNumber", pageNumber);
//        request.setAttribute("totalPages", totalPages);
//
//        List<Entry> entryList = PagingDAO.getEntryList(pageNumber, pageSize);
//        System.out.println("entryList:"+ entryList.size());
//        request.setAttribute("entryList", entryList);
//
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
