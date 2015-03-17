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
        Map showInfo = null;
        if(type.equals("oneWay")){//如果是单程
            request.setAttribute("trip","single");
            //首先获取直飞和转机的航班
            List oneStop = (List) request.getAttribute("goOneStop");
            List nonStop = (List) request.getAttribute("goNonStop");
            showInfo = Pagination.distinguish(nonStop,oneStop,pageNumber);
        }else {//如果有返航
            request.setAttribute("trip","round");
            List pairOne = (List) request.getAttribute("pairOne");
            List pairNon = (List) request.getAttribute("pairNon");
            showInfo = Pagination.distinguish(pairNon, pairOne, pageNumber);
        }
        System.out.println(showInfo);
        request.setAttribute("showInfo", showInfo);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }
}
