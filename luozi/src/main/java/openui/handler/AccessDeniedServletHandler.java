package openui.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedServletHandler implements AccessDeniedHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DEF_ERROR_PAGE_PATH="/error/deniedServlet_denied";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.info("handler AccessDeniedException...");
        response.sendRedirect(DEF_ERROR_PAGE_PATH);
    }
}
