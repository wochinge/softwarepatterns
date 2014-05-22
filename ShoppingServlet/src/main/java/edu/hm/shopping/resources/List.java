package edu.hm.shopping.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.hm.shopping.db.DatabaseService;
import edu.hm.shopping.db.StubDatabase;
import edu.hm.shopping.entities.ShoppingList;
import edu.hm.shopping.utils.UriUtils;

@Path("lists")
public class List {

	@Context 
	private UriInfo uriInfo;
	
	@PathParam("listId") 
	private int listId;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLists() {
		DatabaseService service = new StubDatabase();
		Collection<ShoppingList> lists = service.getLists();
		return Response.ok(lists).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createList(final ShoppingList newList) {
		DatabaseService service = new StubDatabase();
		ShoppingList list = service.createList(newList);
		
		URI created = UriUtils.createURI(uriInfo.getAbsolutePath(), list.getId());
		return Response.created(created).build();
	}
	
	@DELETE
	@Path("{listId}")
	public Response deleteList() {
		DatabaseService service = new StubDatabase();
		try {
			service.deleteList(listId);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.ok().build();
	}
}
