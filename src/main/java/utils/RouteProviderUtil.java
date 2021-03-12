package utils;

import dao.RouteDAO;
import entities.Route;

import java.util.List;

public class RouteProviderUtil {

    private RouteDAO routeDAO;

    public RouteProviderUtil(){
        routeDAO = new RouteDAO();
    }

    public Route getById(int id){
        return routeDAO.getById(id).get();
    }

    public List<Route> getAll(){
        return routeDAO.getAll();
    }

    public void save(Route route){
        routeDAO.save(route);
    }

    public void update(Route route){
        routeDAO.update(route);
    }

    public void delete(Route route){
        routeDAO.delete(route);
    }
}
