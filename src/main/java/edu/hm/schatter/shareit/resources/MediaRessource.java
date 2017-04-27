package edu.hm.schatter.shareit.resources;

import edu.hm.schatter.shareit.businesslayer.MediaService;
import edu.hm.schatter.shareit.businesslayer.MediaServiceImpl;
import edu.hm.schatter.shareit.businesslayer.MediaServiceResult;
import edu.hm.schatter.shareit.models.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/media")
public class MediaRessource {

    private final MediaService mediaService = new MediaServiceImpl();

    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        MediaServiceResult result = mediaService.addBook(book);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn")String isbn) {

        MediaServiceResult result = mediaService.updateBook(isbn, book);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {

        return Response
                .status(Response.Status.OK)
                .entity("json")
                .build();
    }

    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn) {

        return Response
                .status(Response.Status.OK)
                .entity("json")
                .build();
    }


}
