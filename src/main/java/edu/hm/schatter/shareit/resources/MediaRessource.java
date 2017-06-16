package edu.hm.schatter.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.schatter.shareit.businesslayer.MediaService;
import edu.hm.schatter.shareit.businesslayer.MediaServiceResult;
import edu.hm.schatter.shareit.businesslayer.TokenChecker;
import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Provides the API for the share it service.
 */
@Path("/media")
public class MediaRessource {

    private final MediaService mediaService;
    private final TokenChecker tokenChecker;

    /**
     * Injection constructor.
     * @param mediaService injected media service.
     */
    @Inject
    public MediaRessource(MediaService mediaService) {
        this.mediaService = mediaService;
        this.tokenChecker = new TokenChecker();
    }

    /**
     * Testing Constructor.
     * @param mediaService mock.
     * @param tokenChecker mock.
     */
    public MediaRessource(MediaService mediaService, TokenChecker tokenChecker) {
        this.mediaService = mediaService;
        this.tokenChecker = tokenChecker;
    }

    /**
     * Provides the API call for creating a book via a POST parameter.
     * @param book The book that is created. Built from JSON.
     * @param token The token used to authorize the user calling the API.
     * @return Response containing further information on the success of the procedure.
     */
    @POST
    @Path("/books/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book, @PathParam("token")String token) {

        final MediaServiceResult result = tokenChecker.isValid(token)
                ? mediaService.addBook(book)
                : MediaServiceResult.UNAUTHORIZED;

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Provides the API call for updating a book via a PUT parameter.
     * @param book The book containing the updated information. Built from JSON.
     * @param isbn The isbn of the book to be updated.
     * @param token The token used to authorize the user calling the API.
     * @return Response containing further information on the success of the procedure.
     */
    @PUT
    @Path("/books/{isbn}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn")String isbn, @PathParam("token")String token) {

        final MediaServiceResult result = tokenChecker.isValid(token)
                ? mediaService.updateBook(isbn, book)
                : MediaServiceResult.UNAUTHORIZED;

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Provides the API call for listing all the books currently available in the system.
     * @param token The token used to authorize the user calling the API.
     * @return Response containing further information on the success of the procedure
     * including a JSON array of all books available in the system.
     */
    @GET
    @Path("/books/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@PathParam("token")String token) {

        MediaServiceResult result = MediaServiceResult.OK;
        String json = "";

        if (tokenChecker.isValid(token)) {
            final Book[] books = mediaService.getBooks();

            try {
                json = convertToJSON(books);
            } catch (JsonProcessingException e) {
                result = MediaServiceResult.ERROR;
            }
        } else {
            result = MediaServiceResult.UNAUTHORIZED;
        }


        return Response
                .status(result.getStatus())
                .entity(result == MediaServiceResult.OK ? json : result.getJSON())
                .build();
    }

    /**
     * Provides the API call for getting the JSON of a specific book.
     * @param isbn The isbn identifying the book.
     * @param token The token used to authorize the user calling the API.
     * @return Response containing further information on the success of the procedure
     * including the data of the book in JSON format.
     */
    @GET
    @Path("/books/{isbn}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn, @PathParam("token")String token) {

        MediaServiceResult result;
        String json = "";

        if (tokenChecker.isValid(token)) {
            final Book book = mediaService.getBookByISBN(isbn);
            result = book == null ? MediaServiceResult.NOT_FOUND : MediaServiceResult.OK;

            if (result == MediaServiceResult.OK) {
                try {
                    json = convertToJSON(book);
                } catch (JsonProcessingException e) {
                    result = MediaServiceResult.ERROR;
                }
            }
        } else {
            result = MediaServiceResult.UNAUTHORIZED;
        }

        return Response
                .status(result.getStatus())
                .entity(json)
                .build();
    }

    /**
     * Provides the API call for getting the JSON of a specific disc.
     * @param barcode The barcode identifying the disc.
     * @param token The token used to authorize the user calling the API.
     * @return Response containing further information on the success of the procedure
     * including the data of the disc in JSON format.
     */
    @GET
    @Path("/discs/{barcode}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("barcode") String barcode, @PathParam("token")String token) {
        MediaServiceResult result;
        String json = "";

        if (tokenChecker.isValid(token)) {
            final Disc disc = mediaService.getDiscByBarcode(barcode);
            result = disc == null ? MediaServiceResult.NOT_FOUND : MediaServiceResult.OK;

            if (result == MediaServiceResult.OK) {
                try {
                    json = convertToJSON(disc);
                } catch (JsonProcessingException e) {
                    result = MediaServiceResult.ERROR;
                }
            }
        } else {
            result = MediaServiceResult.UNAUTHORIZED;
        }

        return Response
                .status(result.getStatus())
                .entity(json)
                .build();
    }

    /**
     * Provides the API call for listing all the discs currently available in the system.
     * @param token The token used to authorize the user calling the API.
     * @return Response containing further information on the success of the procedure
     * including a JSON array of all discs available in the system.
     */
    @GET
    @Path("/discs/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("token")String token) {
        MediaServiceResult result = MediaServiceResult.OK;
        String json = "";

        if (tokenChecker.isValid(token)) {
            try {
                final Disc[] discs = mediaService.getDiscs();
                json = convertToJSON(discs);
            } catch (JsonProcessingException e) {
                result = MediaServiceResult.ERROR;
            }
        } else {
            result = MediaServiceResult.UNAUTHORIZED;
        }

        return Response
                .status(result.getStatus())
                .entity(result == MediaServiceResult.OK ? json : result.getJSON())
                .build();
    }

    /**
     * Provides the API call for creating a disc via a POST parameter.
     * @param token The token used to authorize the user calling the API.
     * @param disc The disc that is created. Built from JSON.
     * @return Response containing further information on the success of the procedure.
     */
    @POST
    @Path("/discs/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc, @PathParam("token")String token) {
        final MediaServiceResult result = tokenChecker.isValid(token)
                ? mediaService.addDisc(disc)
                : MediaServiceResult.UNAUTHORIZED;

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Provides the API call for updating a disc via a PUT parameter.
     * @param token The token used to authorize the user calling the API.
     * @param disc The disc containing the updated information. Built from JSON.
     * @param barcode The barcode of the book to be updated.
     * @return Response containing further information on the success of the procedure.
     */
    @PUT
    @Path("/discs/{barcode}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode")String barcode, @PathParam("token")String token) {
        final MediaServiceResult result = tokenChecker.isValid(token)
                ? mediaService.updateDisc(barcode, disc)
                : MediaServiceResult.UNAUTHORIZED;

        return Response
                .status(result.getStatus())
                .entity(result.getJSON())
                .build();
    }

    /**
     * Converts objects to json.
     * @param o object to be converted.
     * @return json.
     * @throws JsonProcessingException object could not be converted to json.
     */
    private String convertToJSON(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
