package edu.upc.dsa.services;

import edu.upc.dsa.FactoryImpl;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/auth", description = "Endpoint to authentication service")
@Path("/auth")
public class AuthService {

    private FactoryImpl session;

    public AuthService() {
        this.session = FactoryImpl.getInstance();

    }
    @POST
    @ApiOperation(value = "authenticate user ", notes = "authenticate user given an id and a password ")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "Operation Successfull"),
            @ApiResponse( code = 404, message = "user not found ")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authenticateUser(User user){
       User u = (User)this.session.findById( user.getId(), "usuario");
       if(u != null){
           if(u.getPassword().equals(user.getPassword())){
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
        User u = (User)this.session.findById( user.getId(), "usuario");
        if(u == null){
            session.save(user);
            return Response.status(201).build();
        }
        return Response.status(404).build();
    }

    /*
    @DELETE
    @ApiOperation(value = "delete a User", notes = "Delete a user given an id and a password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Operation Successful"),
            @ApiResponse(code = 404, message = "user not found")
    })
    @Path("/delete")
    public Response deleteUser(User user) {

    }

     */





}