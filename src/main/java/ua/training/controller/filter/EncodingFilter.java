package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(EncodingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("CacheFilter starts... ");

        response.setContentType(EncodingConst.RESP_CONTENT_TYPE);
        response.setCharacterEncoding(EncodingConst.RESP_CHARACTER_ENCODING);
        request.setCharacterEncoding(EncodingConst.REQ_CHARACTER_ENCODING);

        filterChain.doFilter(request, response);
    }

    private static class EncodingConst {
        private static final String RESP_CONTENT_TYPE = "text/html";
        private static final String REQ_CHARACTER_ENCODING = "UTF-8";
        private static final String RESP_CHARACTER_ENCODING = "UTF-8";
    }
}
