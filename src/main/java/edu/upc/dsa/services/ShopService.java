package edu.upc.dsa.services;
import edu.upc.dsa.DAOs.IProductDAOImpl;
import edu.upc.dsa.FactoryImpl;
import edu.upc.dsa.interfaces.IProductDAO;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.ShopProduct;
import edu.upc.dsa.models.User;
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

    private IProductDAO ipd;
    private FactoryImpl session;
    public ShopService() {
        this.session = FactoryImpl.getInstance();
        this.ipd = new IProductDAOImpl(this.session.getConnection());
    }

    @GET
    @ApiOperation(value = "get product list", notes = "List of products")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
            @ApiResponse( code = 404, message = "there aren't any product"),
    })
    @Path("/productList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductList() {

        List<Object> listProducts = this.session.findAll("producto");
        List<Product> productList = (List<Product>)(Object)listProducts;

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productList) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get products by user", notes = "get a list of products")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/productsUser/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByUser(@PathParam("name") String id) {
        List<ShopProduct> listProducts = ipd.findProductsByUser(id);

        if (listProducts == null) return Response.status(404).build();
        else {
            GenericEntity<List<ShopProduct>> entity = new GenericEntity<List<ShopProduct>>(listProducts) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @POST
    @ApiOperation(value = "buy a product", notes = " user buys a product ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "the purchase could not be made")
    })
    @Path("/buyProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyProduct(ShopProduct purchase) {
        Boolean done = ipd.buyProduct(purchase);

        if (done == true){
            return Response.status(201).build();
        }
        else {
            return Response.status(404).build();
        }
    }
}
