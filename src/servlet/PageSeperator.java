package servlet;

import util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
         //获取session
        HttpSession session = request.getSession(false);

        //获取提交参数
        String sortingType = (String)request.getParameter("sorting");

         //获取类型 往返 或者 单程
        String type = (String) session.getAttribute("type");
        String pageNumberStr = request.getParameter("pageNumber");

        int pageNumber = 1;
        if(pageNumberStr!=null && !pageNumberStr.isEmpty())
        {
            pageNumber = Integer.parseInt(pageNumberStr);
        }

        //initialize the sorting method if nothing selected
        if(sortingType == null || sortingType.isEmpty()){
            sortingType = "price(low to high)";
        }

        //将当前页存入session
        session.setAttribute("currentPage",pageNumber);

        List showInfo = null;
        if(type.equals("oneWay")){//如果是单程
            request.setAttribute("trip","single");
            //首先获取直飞和转机的航班
            List oneStop = (List) session.getAttribute("goOneStop");
            List nonStop = (List) session.getAttribute("goNonStop");

            showInfo = Pagination.distinguish(nonStop, oneStop, pageNumber, sortingType);
        }else {//如果有返航
            request.setAttribute("trip","round");
            List pairOne = (List) session.getAttribute("pairOne");
            List pairNon = (List) session.getAttribute("pairNon");
            showInfo = Pagination.distinguish(pairNon, pairOne, pageNumber, sortingType);
        }
        System.out.println(showInfo);
        request.setAttribute("showInfo", showInfo);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }
}
