package servlets;

import entities.Client;
import entities.Route;
import entities.Trip;
import entities.User;
import enums.Country;
import enums.Roots;
import enums.TravelType;
import utils.RouteProviderUtil;
import utils.TripProviderUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="tripsServlet", urlPatterns = "/trips")
public class TripsServlet  extends HttpServlet {
    public final int INSERT_ITEM = 0;
    public final int FIND_ITEM = 1;
    public final int EDIT_ITEM = 2;
    public final int DELETE_ITEM = 3;

    public List<Trip> trips;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        trips = new TripProviderUtil().getAll();
        User u = ((User)req.getSession().getAttribute("currentUser"));
        if(u.getRoots() != Roots.ADMIN){
            Integer clientId = ((User)req.getSession().getAttribute("currentUser")).getClient().getId();
            Cookie c = new Cookie("clientID", clientId.toString());
            resp.addCookie(c);
        }
        req.setAttribute("trips", trips);
        req.getRequestDispatcher("pages/tripsPage.jsp").forward(req,resp);
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
            case EDIT_ITEM:{
                edit(req);
                break;
            }
            case DELETE_ITEM:{
                delete(req);
                break;
            }
        }
        req.getRequestDispatcher("pages/tripsPage.jsp").forward(req,resp);
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
            trips = trips.stream()
                    .filter(t -> t.getDateBegin().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if(searchPar == 2){
            trips = trips.stream()
                    .filter(t -> t.getDateEnd().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if(searchPar == 3){
            trips = trips.stream()
                    .filter(t -> t.getType().name().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
        req.setAttribute("trips", trips);
      }

      private void delete(HttpServletRequest req){
          int tripID = Integer.parseInt(req.getParameter("tripID"));
          System.out.println("trip ID: " + tripID);
          TripProviderUtil tpu = new TripProviderUtil();
          Trip t = tpu.getByID(tripID);
          tpu.delete(t);
      }
    private void edit(HttpServletRequest req){
        int tripID = Integer.parseInt(req.getParameter("tripID"));
        TripProviderUtil tpu = new TripProviderUtil();
        Trip t = tpu.getByID(tripID);
        TravelType tt = TravelType.valueOf(req.getParameter(tripID + "_type"));
        t.setType(tt);
        tpu.update(t);
    }

    private void insert(HttpServletRequest req){
        Trip t = new Trip();
        String beginDateStr = req.getParameter("dbegin_field");
        String[]  strPars1 = beginDateStr.split("-");
        Calendar dbegin = new GregorianCalendar(Integer.parseInt(strPars1[0]), Integer.parseInt(strPars1[1]), Integer.parseInt(strPars1[2]));
        String endDateStr = req.getParameter("dbegin_field");
        String[]  strPars2 = endDateStr.split("-");
        Calendar dend = new GregorianCalendar(Integer.parseInt(strPars2[0]), Integer.parseInt(strPars2[1]), Integer.parseInt(strPars2[2]));
        TravelType tt = TravelType.valueOf(req.getParameter("type_field"));
        Route r = new RouteProviderUtil().getById(Integer.parseInt(req.getParameter("route_field")));
        Client c = ((User)req.getSession().getAttribute("currentUser")).getClient();
        t.setDateBegin(dbegin);
        t.setDateEnd(dend);
        t.setType(tt);
        t.setRoute(r);
        t.setClient(c);
        TripProviderUtil tpu = new TripProviderUtil();
        tpu.save(t);
    }

}
