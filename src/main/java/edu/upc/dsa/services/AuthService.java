package edu.upc.dsa.services;

import edu.upc.dsa.DAOs.IUserDAOImpl;
import edu.upc.dsa.FactoryImpl;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

@Api(value = "/auth", description = "Endpoint to authentication service")
@Path("/auth")
public class AuthService {

    private FactoryImpl session;
    private IUserDAOImpl userDAO;

    public AuthService() {
        this.session = FactoryImpl.getInstance();
        this.userDAO = new IUserDAOImpl(this.session.getConnection());
    }
    @POST
    @ApiOperation(value = "authenticate user ", notes = "authenticate user given an id and a password ")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "Operation Successfull"),
            @ApiResponse( code = 404, message = "user not found ")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authenticateUser(User user) throws NoSuchAlgorithmException {
       User u = (User)this.session.findById( user.getId(), user);
       if(u != null){
           if(this.userDAO.compareMd5(user.getPassword(),u.getPassword())){
               return Response.status(201).build();
           }
       }
        return Response.status(404).build();
    }



    @POST
    @ApiOperation(value = "sign up user ", notes = "Sign up user given an id and a password ")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "Operation Successfull"),
            @ApiResponse( code = 404, message = "user not found ")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/signup")
    public Response signUpUser(User user){
        User u = (User)this.session.findById( user.getId(),user);
        if(u == null){
            session.save(user);
            return Response.status(201).build();
        }
        return Response.status(404).build();
    }






}