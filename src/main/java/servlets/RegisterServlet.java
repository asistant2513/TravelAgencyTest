package servlets;

import entities.Client;
import entities.User;
import enums.Country;
import enums.Roots;
import utils.ClientProviderUtil;
import utils.UserProviderUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private String errorMsg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("pages/regPage.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProviderUtil upu = new UserProviderUtil();
        ClientProviderUtil cpu = new ClientProviderUtil();
        if(upu.getByID(req.getParameter("login_field")) != null){
            errorMsg = "User already exists! Try other login.";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("/pages/regPage.jsp").forward(req,resp);
            return;
        }
        Client c = getClient(req);
        User u = getUser(req);
        if(u == null){
            errorMsg = "Passwords are not the same!";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("/pages/regPage.jsp").forward(req,resp);
            return;
        }
        u.setClient(c);
        cpu.save(c);
        upu.save(u);
        resp.sendRedirect(req.getContextPath() + "/home");
    }

    private Client getClient(HttpServletRequest req){
        Client c = new Client();
        c.setName(req.getParameter("name_field"));
        c.setSurname(req.getParameter("surname_field"));
        c.setCountry(Country.valueOf(req.getParameter("country_field")));
        c.setAge(Integer.parseInt(req.getParameter("age_field")));
        return c;
    }

    private User getUser(HttpServletRequest req) throws ServletException, IOException {
        User u = new User();
        u.setLogin(req.getParameter("login_field"));
        u.setRoots(Roots.USER);
        if(!tryPassword(req)){
            return null;
        }
        else{
            u.setPassword(req.getParameter("pwd_field"));
            return u;
        }
    }

    private boolean tryPassword(HttpServletRequest req){
        String pwd = req.getParameter("pwd_field");
        String rpt = req.getParameter("pwd_field_rpt");
        if(!pwd.equals(rpt))
            return false;
        else
            return true;
    }
}
