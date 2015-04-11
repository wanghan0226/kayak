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
import java.util.List;

/**
 * Created by pianobean on 4/11/15.
 */
@WebServlet(name = "PerfectPage")
public class PerfectPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        HttpSession session = request.getSession(false);
        PageBean pageBean = (PageBean) session.getAttribute("pagebean");
        pageBean.setCurrentPage(pageNumber);
        session.setAttribute("pagebean",pageBean);
        List finalList = (List) session.getAttribute("finalList");
        List showInfo = Pagination.pageResult(finalList, pageNumber);
        session.setAttribute("showInfo",showInfo);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }
}
