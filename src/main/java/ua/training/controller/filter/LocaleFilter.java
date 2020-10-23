package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.Attribute;
import ua.training.controller.command.CommandUtility;
import ua.training.exception.Message;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LocaleFilter.class);

    private static Set<String> locale = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Locale filter initialization starts");
        locale.add(LocaleConst.EN);
        locale.add(LocaleConst.UA);
        logger.debug("Locale filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Processing of the locale filter starts");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String lang = request.getParameter(Attribute.REQUEST_LANG);

        if (lang == null && session.getAttribute(Attribute.SESSION_LANG) == null) {
            CommandUtility.setSession(session, LocaleConst.EN, Attribute.SESSION_LANG);
        } else if (locale.contains(lang)) {
            CommandUtility.setSession(session, lang, Attribute.SESSION_LANG);
        }
        logger.debug("Processing of the locale filter finished");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.debug("Locale filter destruction starts");
        // do nothing
        logger.debug("Locale filter destruction finished");
    }

    private static class LocaleConst {
        private LocaleConst() {
            throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
        }

        public static final String UA = "ua";
        public static final String EN = "en";
    }
}
