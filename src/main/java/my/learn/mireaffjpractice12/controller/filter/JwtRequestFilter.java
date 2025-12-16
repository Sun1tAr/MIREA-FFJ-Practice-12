package my.learn.mireaffjpractice12.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice12.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final AuthService authService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization").substring(7);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication auth;
            try {
                auth = authService.createAuthByAccessJwt(token);
            } catch (Exception e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);

    }
}
