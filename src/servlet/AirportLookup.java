package servlet;

import service.SearchAirport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pianobean on 2/28/15.
 */
@WebServlet(name = "AirportFunctions")
public class AirportLookup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String word = request.getParameter("word").toLowerCase();
        String result = SearchAirport.generateName(word);
        response.getWriter().println(result);

    }
}
