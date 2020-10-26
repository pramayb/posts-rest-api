package nz.co.posts.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nz.co.posts.handler.ServiceHandler;
import nz.co.posts.rest.api.model.Comment;
import nz.co.posts.rest.api.model.Post;

/**
 * The Class PostsRestController.
 */
@RestController
public class PostsRestController {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(PostsRestController.class);

    /** The service handler. */
    @Autowired
    ServiceHandler serviceHandler;

    /**
     * Gets the all available posts.
     *
     * @return the posts
     */
    @GetMapping(value="/posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = null;
        try {
            posts = serviceHandler.getAllPosts();
            log.info("posts found are {}", posts);
            
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(posts, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets the comments which are associated with the post id provided.
     *
     * @param postId the post id
     * @return the comments
     */
    @GetMapping(value="/post/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Integer postId) {
        List<Comment> comments = null;
        try {
            comments = serviceHandler.getCommentsForPost(postId);
            log.info("comments for post {} are {}", postId, comments);
            
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(comments, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
