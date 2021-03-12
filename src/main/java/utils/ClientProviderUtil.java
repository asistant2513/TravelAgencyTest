package utils;

import dao.ClientDAO;
import entities.Client;

import java.util.List;

public class ClientProviderUtil {

    private ClientDAO clientDAO;

    public ClientProviderUtil(){
        clientDAO = new ClientDAO();
    }

    public Client getByID(int id){
        return clientDAO.getById(id).get();
    }

    public List<Client> getAll(){
        return clientDAO.getAll();
    }

    public void save(Client client){
        clientDAO.save(client);
    }

    public void update(Client client){
        clientDAO.update(client);
    }

    public void delete(Client client){
        clientDAO.delete(client);
    }
}
