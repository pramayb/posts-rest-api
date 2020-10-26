package nz.co.posts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The Class PostsAppConfig.
 */
@Configuration
public class PostsAppConfig {
    
    /** The posts endpoint. */
    @Value("${posts.external.endpoint}")
    private String postsEndpoint;

    /** The comments endpoint. */
    @Value("${comments.external.endpoint}")
    private String commentsEndpoint;

    /**
     * Gets the posts endpoint.
     *
     * @return the posts endpoint
     */
    public String getPostsEndpoint() {
        return postsEndpoint;
    }

    /**
     * Gets the comments endpoint.
     *
     * @return the comments endpoint
     */
    public String getCommentsEndpoint() {
        return commentsEndpoint;
    }
    
}
