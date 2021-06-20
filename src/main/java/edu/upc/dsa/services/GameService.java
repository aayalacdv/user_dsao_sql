package edu.upc.dsa.services;

import edu.upc.dsa.DAOs.IGameDAOImpl;
import edu.upc.dsa.DAOs.IUserDAOImpl;
import edu.upc.dsa.FactoryImpl;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.InitGame;
import edu.upc.dsa.models.PlayerUpdate;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/game", description = "Endpoint to game service")
@Path("/game")
public class GameService {
    private FactoryImpl session;
    private IGameDAOImpl dao;
    private IUserDAOImpl iUserDAO;
    public GameService(){
        this.session = FactoryImpl.getInstance();
        this.dao = new IGameDAOImpl(session.getConnection());
        this.iUserDAO = new IUserDAOImpl(session.getConnection());

    }

    //init a game
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/newgame")
    public Response initGame(InitGame init){
        Game game = new Game(init.getUserId()) ;
        this.session.save(game);
        GenericEntity<Game> entity = new GenericEntity<Game>(game){};
        return Response.status(200).entity(entity).build();

    }


    //terminar un juego
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/endgame")
    public Response endGame( Game game){
        this.dao.endGame(game);
        return Response.status(200).entity("Juego terminado").build();
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateplayer")
    public Response updatePlayer(PlayerUpdate player){
        User user = iUserDAO.findById(player.getUserId());
        System.out.println(user);
        float m = player.getMoney() + user.getMoney();
        System.out.println(m);
        user.setMoney(m);
        iUserDAO.update(user.getId(), user);
        return Response.status(201).entity("Jugador actualizado").build();
    }





































}
