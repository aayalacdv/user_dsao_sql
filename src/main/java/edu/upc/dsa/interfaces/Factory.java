package edu.upc.dsa.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Factory {
    public void  connectDatabase();
    public Object findById(String id, Object obj);
    public List<Object> findAll(String obj) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    public void save(Object obj);
    public void delete(Object obj);
    public void update(Object obj);

}
