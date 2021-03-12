package utils;

import dao.UserDAO;
import entities.User;

import java.util.List;

public class UserProviderUtil {

    private UserDAO userDAO;

    public UserProviderUtil(){
        userDAO = new UserDAO();
    }

    public User getByID(String login){
        return (userDAO.getById(login).isPresent()) ? userDAO.getById(login).get(): null;
    }

    public List<User> getAll(){
        return userDAO.getAll();
    }

    public void save(User user){
        userDAO.save(user);
    }

    public void update(User user){
        userDAO.update(user);
    }

    public void delete(User user){
        userDAO.delete(user);
    }
}
