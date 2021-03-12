package servlets;

import entities.Client;
import utils.ClientProviderUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="clientsServlet", urlPatterns = "/clients")
public class ClientsServlet  extends HttpServlet {

    public final int FIND_ITEM = 1;


    public List<Client> clients;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clients = new ClientProviderUtil().getAll();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("pages/clientsPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String par = req.getParameter("actionID");
        int actionID = Integer.parseInt(par);
        System.out.println("Action ID: " + actionID);

        switch (actionID){
            case FIND_ITEM:{
                find(req);
                break;
            }
        }
        req.getRequestDispatcher("pages/clientsPage.jsp").forward(req,resp);
    }

    private void find(HttpServletRequest req){
        int searchPar;
        try{
            searchPar = Integer.parseInt(req.getParameter("search_par"));
        }
        catch (Exception e){
            searchPar = 0;
        }
        String searchQuery = req.getParameter("search_q");
        if(searchPar == 1){
            clients = clients.stream()
                    .filter(c -> c.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if(searchPar == 2){
            clients = clients.stream()
                    .filter(c -> c.getSurname().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if(searchPar == 3){
            int age;
            try {
                age = Integer.parseInt(searchQuery);
            }
            catch(Exception e){ age = 0;}
            int finalCostVal = age;
            clients = clients.stream()
                    .filter(c -> c.getAge() == finalCostVal)
                    .collect(Collectors.toList());
        }
        req.setAttribute("clients", clients);
    }
}
