package servlet;

import factory.ServiceFactory;
import service.AirportService;
import serviceImpl.AirportServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pianobean on 2/28/15.
 */
@WebServlet(name = "AirportDao")
public class AirportLookup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String word = request.getParameter("word").toLowerCase();
        AirportService service = ServiceFactory.getInstance().getAirportService();
        String result = service.generateName(word);
        response.getWriter().println(result);
    }
}
