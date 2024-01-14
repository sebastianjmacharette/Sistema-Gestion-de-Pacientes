package ar.com.macharette.sistema.JWT;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //clase abstracta para crear filtros personalizados

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtener el etoken
        final String token = getTokenFromRequest(request);
        if (token == null)
        {
            filterChain.doFilter(request, response);
            return;
        }
        //llamar nuevamente al filtro para que siga su curso

        filterChain.doFilter(request,response);



    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final  String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        //structural de control para verificar
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")){
            return  authHeader.substring(7);
        }
        return null;

    }
}
