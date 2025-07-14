package project.foodflow.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.Response;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
     
        Response<Object> resp = new Response<>(ReturnCode.UNAUTHORIZED.getCode(), ReturnCode.UNAUTHORIZED.getStatus(), "You need to login to access this resource!", null);
        response.getWriter().write(objectMapper.writeValueAsString(resp));
    }
} 