package edu.hm.schatter.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.schatter.shareit.businesslayer.MediaService;
import edu.hm.schatter.shareit.businesslayer.MediaServiceImpl;
import edu.hm.schatter.shareit.businesslayer.MediaServiceResult;
import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

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

        final Book[] books = mediaService.getBooks();
        MediaServiceResult result = MediaServiceResult.OK;
        String json = "";

        try {
            json = convertToJSON(books);
        } catch (JsonProcessingException e) {
            result = MediaServiceResult.ERROR;
        }

        return Response
                .status(result.getStatus())
                .entity(result == MediaServiceResult.OK ? json : result.getJSON())
                .build();
    }

    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn) {

        final Book book = mediaService.getBookByISBN(isbn);

        MediaServiceResult result = book == null ? MediaServiceResult.NOT_FOUND : MediaServiceResult.OK;
        String json = "";

        if (result == MediaServiceResult.OK) {
            try {
                json = convertToJSON(book);
            } catch (JsonProcessingException e) {
                result = MediaServiceResult.ERROR;
            }
        }

        return Response
                .status(result.getStatus())
                .entity(json)
                .build();
    }

    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("barcode") String barcode) {

        final Disc disc = mediaService.getDiscByBarcode(barcode);

        MediaServiceResult result = disc == null ? MediaServiceResult.NOT_FOUND : MediaServiceResult.OK;
        String json = "";

        if (result == MediaServiceResult.OK) {
            try {
                json = convertToJSON(disc);
            } catch (JsonProcessingException e) {
                result = MediaServiceResult.ERROR;
            }
        }

        return Response
                .status(result.getStatus())
                .entity(json)
                .build();
    }

    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {

        final Disc[] discs = mediaService.getDiscs();

        MediaServiceResult result = MediaServiceResult.OK;
        String json = "";

        try {
            json = convertToJSON(discs);
        } catch (JsonProcessingException e) {
            result = MediaServiceResult.ERROR;
        }

        return Response
                .status(result.getStatus())
                .entity(result == MediaServiceResult.OK ? json : result.getJSON())
                .build();
    }

    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {
        MediaServiceResult result = mediaService.addDisc(disc);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode")String barcode) {
        MediaServiceResult result = mediaService.updateDisc(barcode, disc);

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    private String convertToJSON(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
