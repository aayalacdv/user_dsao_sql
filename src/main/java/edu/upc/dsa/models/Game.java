package edu.upc.dsa.models;

public class Game {
    private String id;
    private String date;
    private String idPlayer;
    private int levelsPassed;
    private float duration;

    public Game(){

    }

    public Game(String id, String date, String idPlayer){
        this.id = id;
        this.date = date;
        this.idPlayer = idPlayer;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getLevelsPassed() {
        return levelsPassed;
    }

    public void setLevelsPassed(int levelsPassed) {
        this.levelsPassed = levelsPassed;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", idPlayer='" + idPlayer + '\'' +
                ", levelsPassed=" + levelsPassed +
                ", duration=" + duration +
                '}';
    }
}
