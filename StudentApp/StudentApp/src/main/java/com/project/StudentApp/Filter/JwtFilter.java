package com.project.StudentApp.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.StudentApp.Service.CustomDetailsServiceImpl;
import com.project.StudentApp.Utility.JwtUtility;


@Component
public class JwtFilter extends OncePerRequestFilter{

	 @Autowired
	    private JwtUtility jwtUtility;
	 @Autowired
	 private CustomDetailsServiceImpl customDetailsServiceImpl;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		  String authorization = request.getHeader("Authorization");
	        String token = null;
	        String userName = null;

	        if(null != authorization && authorization.startsWith("Bearer ")) {
	            token = authorization.substring(7);
	            userName = jwtUtility.getUsernameFromToken(token);
	        }

	        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails
	                    = customDetailsServiceImpl.loadUserByUsername(userName);

	            if(jwtUtility.validateToken(token,userDetails)) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
	                        = new UsernamePasswordAuthenticationToken(userDetails,
	                        null, userDetails.getAuthorities());

	                usernamePasswordAuthenticationToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(request)
	                );

	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }

	        }
	        filterChain.doFilter(request, response);
		
	}

}
