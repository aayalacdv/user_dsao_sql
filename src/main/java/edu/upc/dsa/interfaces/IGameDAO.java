package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.Game;

public interface IGameDAO {
    public void add(Game game);
    public void endGame( Game game);
    public String getMap( int level );
}
