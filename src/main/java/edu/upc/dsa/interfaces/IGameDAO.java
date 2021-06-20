package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.Game;

import java.util.List;

public interface IGameDAO {
    public void add(Game game);
    public void endGame( Game game);
    List<String> getMap(  );
}
