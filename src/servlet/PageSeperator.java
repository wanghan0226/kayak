package servlet;

import beans.PageBean;
import util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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

         //获取类型 往返 或者 单程
        String type = (String) session.getAttribute("type");
        String pageNumberStr = request.getParameter("pageNumber");

        int pageNumber = 1;
        if(pageNumberStr!=null && !pageNumberStr.isEmpty())
        {
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        //将当前页存入session
        session.setAttribute("currentPage",pageNumber);

        List showInfo = null;
        List list = null;
        if(type.equals("oneWay")){//如果是单程
            request.setAttribute("trip","single");
            //首先获取直飞和转机的航班
            Map oneStop = (Map) session.getAttribute("goOneStop");
            Map nonStop = (Map) session.getAttribute("goNonStop");
            list = new ArrayList(nonStop.entrySet());
            list.addAll(oneStop.entrySet());
            session.setAttribute("finalList", list);
            showInfo = Pagination.pageResult(list, pageNumber);
            //分页罗辑

//            showInfo = Pagination.distinguish(nonStop,oneStop,pageNumber);
        }else {//如果有返航
            request.setAttribute("trip","round");
            Map pairOne = (Map) session.getAttribute("pairOne");
            Map pairNon = (Map) session.getAttribute("pairNon");
            list = new ArrayList(pairNon.entrySet());
            list.addAll(pairOne.entrySet());
            session.setAttribute("finalList", list);
            showInfo = Pagination.pageResult(list, pageNumber);
        }
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setTotalRecord(list.size());
        session.setAttribute("pagebean", pageBean);
        System.out.println(pageBean.getPageBar());
        session.setAttribute("showInfo", showInfo);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }
}
