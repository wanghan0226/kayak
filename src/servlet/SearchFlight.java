package servlet;

import factory.ServiceFactory;
import org.dom4j.Document;
import service.AirportService;
import service.NonStopService;
import service.OneStopService;
import service.PairFlights;
import util.AirportNames;
import util.ConstantVariable;
import util.DateFormater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        HttpSession session = request.getSession();
       //先将保存机场名字的map存入session
        Map airportNames = AirportNames.airportNames;
        session.setAttribute("airNames",airportNames);

        //获取提交参数
        String deCode = request.getParameter("deCode");
        String departCode = deCode.substring(deCode.indexOf("(") + 1, deCode.length() - 1);
        String arCode = request.getParameter("arCode");
        String arriveCode = arCode.substring(arCode.indexOf("(")+1,arCode.length()-1);
        String type = request.getParameter("type");
        String deDate = request.getParameter("deDate");
        String arDate = request.getParameter("arDate");
        String seat = request.getParameter("seat");
        int passenger = Integer.parseInt(request.getParameter("passenger"));

        //将座位信息告知下一层
        request.setAttribute("seat", seat);

        //告知下一层是单程还是往返
        request.setAttribute("type",type);

        //分页信息
        String pageNumber = request.getParameter("pageNumber");
        if(pageNumber!=null){
            request.setAttribute("pageNumber", pageNumber);
        }

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
        List flightsNon = nonStop.findNonStopFlights(seat == "First" ? ConstantVariable.FIRST : ConstantVariable.COACH,passenger,arriveCode,deDom);
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

            //将出发航班与返回航班进行匹配
            PairFlights pairUp = ServiceFactory.getInstance().getPairFlights();
            List pairOne = pairUp.pairOneStop(flightsOne, reFlightsOne);
            List pairNon = pairUp.pairNonStop(flightsNon,reFlightsNon);
            request.setAttribute("pairOne",pairOne);
            request.setAttribute("pairNon",pairNon);
        }else {
            //放入request域
            request.setAttribute("goOneStop",flightsOne);
            request.setAttribute("goNonStop",flightsNon);
        }
        request.getRequestDispatcher("/page").forward(request,response);
    }

}
