package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CacheFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(CacheFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("CacheFilter starts... ");

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        resp.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
        resp.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"

        chain.doFilter(request, response);
    }

}
