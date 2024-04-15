package elisadari.UN5W3d1praticaS3L1.security;

import elisadari.UN5W3d1praticaS3L1.exceptions.UnauthorizedEx;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    JWTTools jwtTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String aHeader=request.getHeader("Authorization");
        if(aHeader.equals(null)||!aHeader.startsWith("Bearer "))throw new UnauthorizedEx("manca il token per l'autorizzazione");
        String token=aHeader.substring(7);
        jwtTools.verifyToken(token);
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**",request.getServletPath());
    }
}
