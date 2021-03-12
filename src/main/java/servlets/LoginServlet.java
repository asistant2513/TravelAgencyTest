package servlets;

import entities.User;
import utils.UserProviderUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("pages/loginPage.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        String login = req.getParameter("login_field");
        String password = req.getParameter("pwd_field");

        User user = new UserProviderUtil().getByID(login);
        if(user != null){
            if(user.getPassword().equals(password)){
                session.setAttribute("currentUser", user);
                session.setAttribute("roots", user.getRoots());
                resp.sendRedirect(req.getContextPath() + "/home");
            }
            else{
                req.setAttribute("error", "Wrong password!");
                req.getRequestDispatcher("pages/loginPage.jsp").forward(req, resp);
            }
        }
        else{
            req.setAttribute("error", "User Not Found!");
            req.getRequestDispatcher("pages/loginPage.jsp").forward(req, resp);
        }
    }
}
