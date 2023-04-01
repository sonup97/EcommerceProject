package com.ytecomm.jwt.configuration;

import com.ytecomm.jwt.service.JwtService;
import com.ytecomm.jwt.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = null;
        String userName = null;


        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String header1 = httpServletRequest.getHeader("Authorization");
        System.out.println(header1);


        System.out.println(requestTokenHeader);
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            System.out.println("Inside If Block to get Brerer token");
            jwtToken = requestTokenHeader.substring(7);
            System.out.println(jwtToken);

            try{
                userName = jwtUtil.getUserNameFromToken(jwtToken);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt token is expired");
            }
        } else {
            System.out.println("Jwt token does not start with Bearer");
        }

        if (userName!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
           UserDetails userDetails =  jwtService.loadUserByUsername(userName);

           if (jwtUtil.validateToken(jwtToken,userDetails)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,
                       userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}