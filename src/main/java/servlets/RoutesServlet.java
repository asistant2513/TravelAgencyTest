package servlets;

import entities.Route;
import enums.Country;
import utils.RouteProviderUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="routesServlet", urlPatterns = "/routes")
public class RoutesServlet  extends HttpServlet {

    public final int INSERT_ITEM = 0;
    public final int FIND_ITEM = 1;
    public final int DELETE_ITEM = 3;
    public final int EDIT_ITEM = 2;

    public List<Route> routes;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        routes = new RouteProviderUtil().getAll();
        req.setAttribute("routes", routes);
        req.getRequestDispatcher("pages/routesPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String par = req.getParameter("actionID");
        int actionID = Integer.parseInt(par);
        System.out.println("Action ID: " + actionID);

        switch (actionID){
            case INSERT_ITEM:{
                insert(req);
                break;
            }
            case FIND_ITEM:{
                find(req);
                break;
            }
            case DELETE_ITEM:{
                delete(req);
                break;
            }
            case EDIT_ITEM:{
                edit(req);
                break;
            }
        }
        req.getRequestDispatcher("pages/routesPage.jsp").forward(req,resp);
    }

    private void insert(HttpServletRequest req) throws ServletException, IOException {
        Route route = new Route();
        Country from = Country.valueOf(req.getParameter("from_field"));
        Country to = Country.valueOf(req.getParameter("to_field"));
        int cost = Integer.parseInt(req.getParameter("cost_field"));
        route.setPointBegin(from);
        route.setPointEnd(to);
        route.setCost(cost);
        RouteProviderUtil rpu = new RouteProviderUtil();
        rpu.save(route);
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
        List<Route> searchRes;
        if(searchPar == 1){
            routes = routes.stream()
                    .filter(r -> r.getPointBegin().toString().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if(searchPar == 2){
            routes = routes.stream()
                    .filter(r -> r.getPointEnd().toString().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if(searchPar == 3){
            int costVal;
            try {
                costVal = Integer.parseInt(searchQuery);
            }
            catch(Exception e){ costVal = 0;}
            int finalCostVal = costVal;
            routes = routes.stream()
                    .filter(r -> r.getCost() == finalCostVal)
                    .collect(Collectors.toList());
        }
        req.setAttribute("routes", routes);
    }

    private void delete(HttpServletRequest req){
        int routeID = Integer.parseInt(req.getParameter("routeID"));
        System.out.println("Route ID: " + routeID);
        RouteProviderUtil rpu = new RouteProviderUtil();
        Route r = rpu.getById(routeID);
        rpu.delete(r);
    }

    private void edit(HttpServletRequest req){
        int routeID = Integer.parseInt(req.getParameter("routeID"));
        String from = req.getParameter(routeID + "_from");
        String to = req.getParameter(routeID + "_to");
        int cost = Integer.parseInt(req.getParameter(routeID + "_cost"));
        RouteProviderUtil rpu = new RouteProviderUtil();
        Route r = rpu.getById(routeID);
        r.setPointBegin(Country.valueOf(from));
        r.setPointEnd(Country.valueOf(to));
        r.setCost(cost);
        rpu.update(r);
    }
}
