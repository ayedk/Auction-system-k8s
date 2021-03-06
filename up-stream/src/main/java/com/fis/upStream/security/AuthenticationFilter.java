package com.fis.upStream.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            com.fis.upStream.model.User creds = new ObjectMapper().
                    readValue(request.getInputStream(), com.fis.upStream.model.User.class);
            return authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
                            creds.getPassword(),new ArrayList<>()));
        }catch(IOException e) {
            throw new RuntimeException("Could not read request" + e);
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws IOException {

        String token = Jwts.builder()
        .setSubject(((User) authentication.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
        .signWith(SignatureAlgorithm.HS512, "mysecret".getBytes())
        .compact();
        JSONObject entity = new JSONObject();
        entity.put("Authorization","Bearer " + token);
        entity.put("username",((User) authentication.getPrincipal()).getUsername());
        response.getWriter().print(entity);
        response.getWriter().flush();

    }
}