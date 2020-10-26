package nz.co.posts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Class CORSFilter.
 * Mainly used to bypass CORS exception encountered when integrated with localhost:8080
 */
@Configuration
@EnableWebMvc
public class CORSFilter implements Filter {

    /**
     * Do filter to allow header parameter "Access-Control-Allow-Origin", which
     * causes CORS error at the client due to localhost:8080
     *
     * @param req the req
     * @param res the res
     * @param chain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        chain.doFilter(req, res);
    }

    /**
     * Inits the.
     *
     * @param filterConfig the filter config
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
    }

}
