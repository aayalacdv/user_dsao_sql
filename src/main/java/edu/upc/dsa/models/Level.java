package edu.upc.dsa.models;

public class Level {


    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Level (){}
    public Level (String level){
        this.level = level;

    }

    @Override
    public String toString() {
        return "Level{" +
                "level='" + level + '\'' +
                '}';
    }
}
