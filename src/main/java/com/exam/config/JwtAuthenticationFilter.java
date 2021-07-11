package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.exam.services.impl.UserDetailsServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Inside filter");
		String requestHeader=request.getHeader("Authorization");
		String jwtToken=null;
		String username=null;
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer ")) {
			//extracting token and username
			jwtToken=requestHeader.substring(7);
			try {
			username=jwtUtil.extractUsername(jwtToken);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
					boolean b= this.jwtUtil.validateToken(jwtToken, userDetails);
					if(b) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
						
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						
						
					}
					else {
						System.out.println("Token is not valid");
					}
			}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("Jwt Token has expired");
			}
		}
		else
		{
			System.out.println("Invalid Token or user doesnt start with Bearer");
		}
		
		//validate token
		
		
		filterChain.doFilter(request, response);
		}
	}


