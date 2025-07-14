package project.foodflow.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.Response;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        Response<Object> resp = new Response<>(ReturnCode.FORBIDDEN.getCode(), ReturnCode.FORBIDDEN.getStatus(), "You don't have permission to access this resource!", null);
        response.getWriter().write(objectMapper.writeValueAsString(resp));
    }
} 