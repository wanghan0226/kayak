package servlet;

import beans.PageBean;
import factory.ServiceFactory;
import service.SortFlights;
import util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by pianobean on 4/10/15.
 */
@WebServlet(name = "SortingServlet")
public class SortingServlet extends HttpServlet {
    ServiceFactory service = ServiceFactory.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List oldInfo = (List) session.getAttribute("finalList");


        String sortingType = request.getParameter("type");
        System.out.println(sortingType);
        SortFlights sortMethods = service.getSortedFlights();
        List newInfo =null;
        if (sortingType.equals("1")){
            session.setAttribute("selected","priceAsc");
            newInfo = sortMethods.sortByPrice(oldInfo);
        }else if(sortingType.equals("3")){
            session.setAttribute("selected","timeAsc");
            newInfo = sortMethods.sortByTime(oldInfo);
        }else if(sortingType.equals("2")){
            session.setAttribute("selected","priceDes");
            newInfo = sortMethods.sortByPriceDec(oldInfo);
        }else {
            session.setAttribute("selected","timeDes");
            newInfo = sortMethods.sortByTimeDec(oldInfo);
        }

        PageBean pageBean = (PageBean) session.getAttribute("pagebean");
        pageBean.setCurrentPage(1);

        session.setAttribute("pagebean",pageBean);
        session.setAttribute("finalList", newInfo);
        List showInfo = Pagination.pageResult(newInfo,1);
        session.setAttribute("showInfo",showInfo);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }
}
