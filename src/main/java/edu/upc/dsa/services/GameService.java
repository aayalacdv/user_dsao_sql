package edu.upc.dsa.services;

import edu.upc.dsa.FactoryImpl;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/game", description = "Endpoint to game service")
@Path("/game")
public class GameService{

    private List<String> testMap;
    private String mapId;
    private Map map;

    public GameService() {
        this.testMap = new ArrayList<>();
        this.mapId = "map1";
        //add map objects
        this.testMap.add("Items");
        this.testMap.add("Floor");
        this.map = new Map(mapId);
        this.map.setItems(testMap);

    }


    @GET
    @ApiOperation(value = "Get a map ", notes = "Get the objects that describe a map ")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "Operation Successfull"),
            @ApiResponse( code = 404, message = "Map not found")
    })
    @Path("/maps/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaps(@PathParam("id") String id ){
        if(id.equals(mapId)){
            GenericEntity<Map> entity = new GenericEntity<Map>(map){};
            return Response.status(201).entity(entity).build();
        }
        return Response.status(404).build();

    }



}