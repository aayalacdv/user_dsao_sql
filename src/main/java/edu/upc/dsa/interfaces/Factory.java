package edu.upc.dsa.interfaces;

import java.util.List;

public interface Factory {
    public void  connectDatabase();
    public Object findById(String id, String obj);
    public List<Object> findAll(String obj);
    public void save(Object obj);
    public void delete(Object obj);
    public void update(Object obj);

}
