package edu.hm.schatter.shareit.resources;

import edu.hm.schatter.shareit.businesslayer.MediaService;
import edu.hm.schatter.shareit.businesslayer.MediaServiceResult;
import edu.hm.schatter.shareit.businesslayer.TokenChecker;
import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MediaRessourceTest {

    private MediaRessource mediaRessource;

    private static final String MOCK_TOKEN = "";
    private static final Book MOCK_BOOK = null;
    private static final Disc MOCK_DISC = null;

    private TokenChecker trueTokenChecker() {
        TokenChecker tokenCheckerMock = mock(TokenChecker.class);
        when(tokenCheckerMock.isValid(MOCK_TOKEN)).thenReturn(true);
        return tokenCheckerMock;
    }

    private TokenChecker falseTokenChecker() {
        TokenChecker tokenCheckerMock = mock(TokenChecker.class);
        when(tokenCheckerMock.isValid(MOCK_TOKEN)).thenReturn(false);
        return tokenCheckerMock;
    }

    @Before
    public void setUp() {
        mediaRessource = null;
    }

    @Test
    public void createBookWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.addBook(MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.createBook(MOCK_BOOK, MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void createBookWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.addBook(MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.createBook(null, MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    @Test
    public void updateBookWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.updateBook("isbn", MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.updateBook(MOCK_BOOK, "isbn", MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void updateBookWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.updateBook("isbn", MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.updateBook(MOCK_BOOK, "isbn", MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    @Test
    public void getBooksWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.updateBook("isbn", MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.getBooks(MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void getBooksWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.updateBook("isbn", MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.getBooks(MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    @Test
    public void getBookReturnsExistingBook() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.getBookByISBN("isbn")).thenReturn(new Book("", "", ""));
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.getBook("isbn", MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void getBookInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.getBookByISBN("isbn")).thenReturn(null);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.getBook("isbn", MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    // -----------------------------------------------------------------------------------------

    @Test
    public void createDiscWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.addDisc(MOCK_DISC)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.createDisc(MOCK_DISC, MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void createDiscWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.addDisc(MOCK_DISC)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.createDisc(MOCK_DISC, MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    @Test
    public void updateDiscWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.updateDisc("barcode", MOCK_DISC)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.updateDisc(MOCK_DISC, "barcode", MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void updateDiscWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.updateDisc("barcode", MOCK_DISC)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.updateDisc(MOCK_DISC, "barcode", MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    @Test
    public void getDiscsWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.getDiscs(MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void getDiscWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.getDiscs(MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }

    @Test
    public void getDiscReturnsExistingDisc() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.getDiscByBarcode("barcode")).thenReturn(new Disc("", "", "", 0));
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.getDiscs("barcode", MOCK_TOKEN);

        assertEquals(200, result.getStatus());
    }

    @Test
    public void getDiscInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.getDiscByBarcode("barcode")).thenReturn(null);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.getDiscs("barcode", MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }
}