package edu.upc.dsa.services;
import edu.upc.dsa.DAOs.IProductoDAOImpl;
import edu.upc.dsa.interfaces.IProductoDAO;
import edu.upc.dsa.models.Buys;
import edu.upc.dsa.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/shop", description = "Endpoint to shop service")
@Path("/shop")
public class ShopService {

    private IProductoDAO ipd;
    public ShopService() {
        this.ipd = IProductoDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get product list", notes = "List of products")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/productList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByPrize() {

        List<Product> listProducts = this.ipd.getProducts();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(listProducts) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get products by user", notes = "get a list of products")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/productsUser/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectsByUser(@PathParam("name") String id) {
        List<Product> listObject = ipd.getProductsByUser(id);

        if (listObject == null) return Response.status(404).build();
        else {
            GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(listObject) {};
            return Response.status(201).entity(entity).build();
        }
    }
/*
    @GET
    @ApiOperation(value = "buy a product", notes = " user buys a product ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjectGame.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/buyProduct/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyProduct(Buys buys) {
        List<ObjectGame> listObject = gm.getObjects(id);

        if (listObject == null) return Response.status(404).build();
        else {
            GenericEntity<List<ObjectGame>> entity = new GenericEntity<List<ObjectGame>>(listObject) {};
            return Response.status(201).entity(entity).build();
        }
    }
*/

    }
