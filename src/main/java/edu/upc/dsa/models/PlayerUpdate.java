package edu.upc.dsa.models;

public class PlayerUpdate {

    private String userId;
    private int money;

    public PlayerUpdate() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PlayerUpdate{" +
                "userId='" + userId + '\'' +
                ", money=" + money +
                '}';
    }
}
