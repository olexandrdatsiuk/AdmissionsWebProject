package ua.training.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ServletTest {

    private Servlet servlet;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;

    @Before
    public void init() {
        servlet = new Servlet();
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }


    @Test
    public void servlet_should_send_redirect() throws IOException, ServletException {
        String path = "/AdmissionsWebProject/login";
        when(req.getRequestURI()).thenReturn(path);
        servlet.doGet(req, resp);
        verify(resp, times(1)).sendRedirect(anyString());
        verify(req, times(0)).getRequestDispatcher(path);
    }
}
