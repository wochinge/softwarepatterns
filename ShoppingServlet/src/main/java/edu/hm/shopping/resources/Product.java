package edu.hm.shopping.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.hm.shopping.db.DatabaseService;
import edu.hm.shopping.db.StubDatabase;
import edu.hm.shopping.entities.ListItem;
import edu.hm.shopping.utils.UriUtils;

@Path("lists/{listId}/products")
public class Product {

	@Context 
	private UriInfo uriInfo;
	
	@PathParam("listId")
	private int listId;
	
	@PathParam("productId") 
	private int productId;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		DatabaseService service = new StubDatabase();
		Collection<ListItem> products;
		try {
			products = service.getProducts(listId);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(products).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(final ListItem item) {
		DatabaseService service = new StubDatabase();
		ListItem createdProduct;
		try {
			createdProduct = service.createProduct(listId, item);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		URI created = UriUtils.createURI(uriInfo.getAbsolutePath(), createdProduct.getId());
		return Response.created(created).build();
	}
	
	@PUT
	@Path("{productId}")
	public Response updateProduct(final ListItem product) {
		DatabaseService service = new StubDatabase();
		try {
			service.updateProduct(listId, productId, product);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{productId}")
	public Response deleteProduct() {
		DatabaseService service = new StubDatabase();
		try {
			service.deleteProduct(listId, productId);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.ok().build();
	}
}
