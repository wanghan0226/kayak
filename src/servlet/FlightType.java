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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pianobean on 4/10/15.
 */
@WebServlet(name = "FlightType")
public class FlightType extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("type");

        session.setAttribute("selected","");

        String one = request.getParameter("one");
        String non = request.getParameter("non");
        System.out.println(type);
        System.out.println("one:"+one+"----non:"+non);
//        System.out.println(stopTypes);
        List list = null;
        List typeList = null;
        if(type.equals("oneWay")) {//如果是单程
            Map oneStop = (Map) session.getAttribute("goOneStop");
            Map nonStop = (Map) session.getAttribute("goNonStop");

            if(!one.equals("checked") && non.equals("checked")){
                System.out.println("what the fuck");
                String[] flightType = {"NON"};
                session.setAttribute("showType",flightType);
                typeList = new ArrayList(nonStop.entrySet());
                session.setAttribute("finalList", typeList);
                list = Pagination.pageResult(typeList,1);
            }else if(one.equals("checked") && !non.equals("checked")){

                String[] flightType = {"ONE"};
                session.setAttribute("showType",flightType);

                typeList = new ArrayList(oneStop.entrySet());
                session.setAttribute("finalList", typeList);
                list = Pagination.pageResult(typeList,1);
            }else if(!one.equals("checked") && !non.equals("checked")){

                session.setAttribute("showType",null);

            }else {

                String[] flightType = {"NON","ONE"};
                session.setAttribute("showType",flightType);

                request.getRequestDispatcher("/page").forward(request,response);
                return;
            }

        }else {
            Map pairOne = (Map) session.getAttribute("pairOne");
            Map pairNon = (Map) session.getAttribute("pairNon");

            if(!one.equals("checked") && non.equals("checked")){

                String[] flightType = {"NON"};
                session.setAttribute("showType",flightType);

                typeList = new ArrayList(pairNon.entrySet());
                session.setAttribute("finalList", typeList);
                list = Pagination.pageResult(typeList,1);
            }else if(one.equals("checked") && !non.equals("checked")){

                String[] flightType = {"ONE"};
                session.setAttribute("showType",flightType);

                typeList = new ArrayList(pairOne.entrySet());
                session.setAttribute("finalList", typeList);
                list = Pagination.pageResult(typeList,1);
            }else if(!one.equals("checked") && !non.equals("checked")){

                session.setAttribute("showType",null);

            }else {

                String[] flightType = {"NON","ONE"};
                session.setAttribute("showType",flightType);

                request.getRequestDispatcher("/page").forward(request,response);
                return;
            }
        }

        PageBean pageBean = new PageBean();
        if(typeList==null) typeList = new ArrayList();
        pageBean.setTotalRecord(typeList.size());
        pageBean.setCurrentPage(1);
        session.setAttribute("pagebean",pageBean);
        session.setAttribute("showInfo", list);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }
}
