package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.User;

import java.util.List;

public interface IUserDAO {
    public User findUsuariobyId(String id);
    public List<User> findAll();
    public void addUsuario(User user);
    public void deleteUsuario(String id, String pass);
    public void updateUsuario(String id, User user);
    //public List<Producto> findProductsByUser(String id);

}
