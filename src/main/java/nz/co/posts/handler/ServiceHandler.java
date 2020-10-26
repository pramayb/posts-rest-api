package nz.co.posts.handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nz.co.posts.config.PostsAppConfig;
import nz.co.posts.rest.api.model.Comment;
import nz.co.posts.rest.api.model.Post;

/**
 * The Class ServiceHandler- To interact with third party services or storage
 */
@Service
public class ServiceHandler {

    /** The posts app config. */
    @Autowired
    private PostsAppConfig postsAppConfig;
    
    /**
     * Gets the all posts.
     *
     * @return the all posts
     * @throws URISyntaxException the URI syntax exception
     */
    public List<Post> getAllPosts() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(postsAppConfig.getPostsEndpoint());

        List<Post> result = restTemplate.getForObject(uri, List.class);
        return result;
    }

    /**
     * Gets the comments for post.
     *
     * @param postId the post id
     * @return the comments for post
     * @throws URISyntaxException the URI syntax exception
     */
    public List<Comment> getCommentsForPost(Integer postId) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = postsAppConfig.getCommentsEndpoint().replace("#postId#", postId.toString());
        URI uri = new URI(baseUrl);

        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        
        return result.getBody();
    }

}
