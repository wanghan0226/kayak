package servlet;

import factory.ServiceFactory;
import org.dom4j.Document;
import service.AirportService;
import service.NonStopService;
import service.OneStopService;
import util.ConstantVariable;
import util.DateFormater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pianobean on 3/2/15.
 */
@WebServlet(name = "SearchFlight")
public class SearchFlight extends HttpServlet {
    private AirportService service = ServiceFactory.getInstance().getAirportService();
    private OneStopService oneStop = ServiceFactory.getInstance().getOneStopService();
    private NonStopService nonStop = ServiceFactory.getInstance().getNonStopService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deCode = request.getParameter("deCode");
        String departCode = deCode.substring(deCode.indexOf("(") + 1, deCode.length() - 1);
        String arCode = request.getParameter("arCode");
        String arriveCode = arCode.substring(arCode.indexOf("(")+1,arCode.length()-1);
        String type = request.getParameter("type");
        String deDate = request.getParameter("deDate");
        String arDate = request.getParameter("arDate");
        String seat = request.getParameter("seat");
        int passenger = Integer.parseInt(request.getParameter("passenger"));
        //获取出发机场dom文件
        Date depart = DateFormater.format(deDate);
        Document deDom = service.getDepartDom(departCode, depart);
        //获取当天到达机场dom文件
        Document arDom = service.getArriveDom(arriveCode, depart);
        //获取第二天到达机场的dom文件
        Calendar cal = Calendar.getInstance();
        cal.setTime(depart);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDay = cal.getTime();
        Document nextDom = service.getArriveDom(arriveCode, nextDay);

        //获取一次转机的匹配集合
        List flightsOne = oneStop.validOneStop(seat == "First" ? ConstantVariable.FIRST : ConstantVariable.COACH, passenger, deDom, arDom, nextDom);
        //获取直达航班
        List flightNon = nonStop.findNonStopFlights(seat == "First" ? ConstantVariable.FIRST : ConstantVariable.COACH,passenger,arriveCode,deDom);

        //放入request域
        request.setAttribute("goOneStop",flightsOne);
        request.setAttribute("goNonStop",flightNon);
        /*
            返程航班
         */
        if(arDate!=null){
            //获取出发机场dom文件
            Date returnDepart = DateFormater.format(arDate);
            Document returnDeDom = service.getDepartDom(arriveCode, returnDepart);
            //获取当天到达机场dom文件
            Document returnArDom = service.getArriveDom(departCode,returnDepart);
            //获取第二天到达机场的dom文件
            cal.setTime(returnDepart);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date returnNext = cal.getTime();
            Document returnNextDom = service.getArriveDom(departCode, returnNext);

            List reFlightsOne = oneStop.validOneStop(seat == "First" ? ConstantVariable.FIRST : ConstantVariable.COACH,passenger,returnDeDom,returnArDom,returnNextDom);
            List reFlightsNon = nonStop.findNonStopFlights(seat == "First" ? ConstantVariable.FIRST : ConstantVariable.COACH,passenger,departCode,returnDeDom);
            request.setAttribute("comeOneStop",reFlightsOne);
            request.setAttribute("comeNonStop",reFlightsNon);
        }
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
    }

}
