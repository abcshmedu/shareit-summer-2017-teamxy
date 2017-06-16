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
    public void addingBookWithValidTokenReturns200() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.addBook(MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, trueTokenChecker());

        Response result = mediaRessource.createBook(MOCK_BOOK, MOCK_TOKEN);

        assertEquals(200, result.getStatus());

        System.out.println(result);
    }

    @Test
    public void addingBookWithInvalidTokenReturns401() {
        MediaService mediaServiceMock = mock(MediaService.class);
        when(mediaServiceMock.addBook(MOCK_BOOK)).thenReturn(MediaServiceResult.OK);
        mediaRessource = new MediaRessource(mediaServiceMock, falseTokenChecker());

        Response result = mediaRessource.createBook(null, MOCK_TOKEN);

        assertEquals(401, result.getStatus());
    }
}