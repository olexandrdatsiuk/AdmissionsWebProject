package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.CommandUtility;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LocaleFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("LocaleFilter starts...");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String lang = request.getParameter("lang");

        // todo do check

        if (lang == null) {
            if (session.getAttribute("role") == null) {
                CommandUtility.setSession(session, "en", "lang");
            }
        } else {
            CommandUtility.setSession(session, lang, "lang");
        }
        logger.debug("Lang attr set to: " + session.getAttribute("lang"));

        filterChain.doFilter(request, response);
    }
}
