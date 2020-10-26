package nz.co.posts.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import nz.co.posts.handler.ServiceHandler;
import nz.co.posts.rest.api.model.Comment;
import nz.co.posts.rest.api.model.Post;

/**
 * The Class PostsRestControllerTest.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
class PostsRestControllerTest {
    
    /** The Constant EXPECTED_EMPTY_ARRAY. */
    private static final String EXPECTED_EMPTY_ARRAY = "[]";

    /** The service handler. */
    @MockBean
    private ServiceHandler serviceHandler;
    
    /** The rest template. */
    @Autowired
    private TestRestTemplate restTemplate;
    
    /**
     * Test get posts.
     *
     * @throws URISyntaxException the URI syntax exception
     */
    @Test
    final void testGetPosts() throws URISyntaxException {
        List<Post> posts = new ArrayList<>();
        when(serviceHandler.getAllPosts()).thenReturn(posts);
        
        ResponseEntity<String> response = restTemplate.getForEntity("/posts", String.class);
        assertEquals( org.springframework.http.HttpStatus.OK, response.getStatusCode());
        verify(serviceHandler, Mockito.times(1)).getAllPosts();
        assertEquals(EXPECTED_EMPTY_ARRAY, response.getBody());
    }

    /**
     * Test get posts failure.
     *
     * @throws URISyntaxException the URI syntax exception
     */
    @Test
    final void testGetPostsFailure() throws URISyntaxException {
        when(serviceHandler.getAllPosts()).thenThrow(URISyntaxException.class);
        
        ResponseEntity<String> response = restTemplate.getForEntity("/posts", String.class);
        assertEquals( org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(serviceHandler, Mockito.times(1)).getAllPosts();
    }

    /**
     * Test get comments.
     *
     * @throws URISyntaxException the URI syntax exception
     */
    @Test
    final void testGetComments() throws URISyntaxException {
        List<Comment> comments = new ArrayList<>();
        when(serviceHandler.getCommentsForPost(1)).thenReturn(comments);

        ResponseEntity<String> response = restTemplate.getForEntity("/post/1/comments", String.class);
        assertEquals( org.springframework.http.HttpStatus.OK, response.getStatusCode());
        verify(serviceHandler, Mockito.times(1)).getCommentsForPost(1);
        assertEquals(EXPECTED_EMPTY_ARRAY, response.getBody());
    }

    /**
     * Test get comments failure.
     *
     * @throws URISyntaxException the URI syntax exception
     */
    @Test
    final void testGetCommentsFailure() throws URISyntaxException {
        when(serviceHandler.getCommentsForPost(1)).thenThrow(URISyntaxException.class);
        
        ResponseEntity<String> response = restTemplate.getForEntity("/post/1/comments", String.class);
        assertEquals( org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(serviceHandler, Mockito.times(1)).getCommentsForPost(1);
    }
    
}
