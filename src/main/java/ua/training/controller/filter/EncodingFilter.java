package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.exception.Message;

import javax.servlet.*;
import java.io.IOException;

/**
 * Represents an encoding filter. This class implements
 * Filter and overrides the main methods of interface.
 * EncodingFilter provides main setting up of content
 * connected to encoding for data processing.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class EncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(EncodingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Processing of the encoding filter starts");

        response.setContentType(EncodingConst.RESP_CONTENT_TYPE);
        response.setCharacterEncoding(EncodingConst.RESP_CHARACTER_ENCODING);
        request.setCharacterEncoding(EncodingConst.REQ_CHARACTER_ENCODING);

        logger.debug("Processing of the encoding filter finished");
        filterChain.doFilter(request, response);
    }

    private static class EncodingConst {
        private EncodingConst() {
            throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
        }

        private static final String RESP_CONTENT_TYPE = "text/html";
        private static final String REQ_CHARACTER_ENCODING = "UTF-8";
        private static final String RESP_CHARACTER_ENCODING = "UTF-8";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Encoding filter initialization starts");
        // do nothing
        logger.debug("Encoding filter initialization finished");
    }

    @Override
    public void destroy() {
        logger.debug("Encoding filter destruction starts");
        // do nothing
        logger.debug("Encoding filter destruction finished");
    }
}
