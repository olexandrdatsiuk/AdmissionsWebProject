package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represents a cache filter. This class implements
 * Filter and overrides the main methods of interface.
 * CacheFilter provides cache control setting.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class CacheFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(CacheFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Processing of the cache filter starts");

        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Cache-Control", "no-store");
        resp.setDateHeader("Expires", 0);

        chain.doFilter(request, response);

        logger.debug("Processing of the cache filter finished");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Cache filter initialization starts");
        // do nothing
        logger.debug("Cache filter initialization finished");
    }

    @Override
    public void destroy() {
        logger.debug("Cache filter destruction starts");
        // do nothing
        logger.debug("Cache filter destruction finished");
    }
}
