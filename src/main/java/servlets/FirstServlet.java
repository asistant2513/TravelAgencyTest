package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="firstServlet", urlPatterns = "/")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        if(req.getSession().getAttribute("currentUser") == null){
           rd = req.getRequestDispatcher("pages/index.jsp");
        }
        else{
            rd = req.getRequestDispatcher("pages/homePage.jsp");
        }
        rd.forward(req,resp);
    }
}
