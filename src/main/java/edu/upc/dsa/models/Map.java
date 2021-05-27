package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private String name;
    private List<String> items;

    public Map(){

    }

    public Map( String name ){
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Map{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
