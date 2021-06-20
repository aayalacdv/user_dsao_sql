package edu.upc.dsa.models;


public class InitGame {

    String userId;

    public InitGame(){

    }

    public InitGame( String userId){
        this.userId = userId;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "InitGame{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
