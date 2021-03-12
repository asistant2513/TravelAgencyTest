package utils;

import dao.TripDAO;
import entities.Trip;

import java.util.List;

public class TripProviderUtil {

    private TripDAO tripDAO;

    public TripProviderUtil(){
        tripDAO = new TripDAO();
    }

    public Trip getByID(int id){
        return tripDAO.getById(id).get();
    }

    public List<Trip> getAll(){
        return tripDAO.getAll();
    }

    public void save(Trip trip){
        tripDAO.save(trip);
    }

    public void update(Trip trip){
        tripDAO.update(trip);
    }

    public void delete(Trip trip){
        tripDAO.delete(trip);
    }
}
