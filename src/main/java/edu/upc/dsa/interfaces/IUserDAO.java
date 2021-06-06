package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IUserDAO {
    public User findById(String id)throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    public List<User> findAll();
    public void add(User user);
    public void delete(String id, String pass);
    public void update(String id, User user);
    //public List<Producto> findProductsByUser(String id);

}
