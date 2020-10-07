package com.assessment.hsbc.jwt.filter;

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

import com.assessment.hsbc.jwt.Utils.JwtUtil;
import com.assessment.hsbc.security.MyUserDetailsService;

@Component
public class JwtValidateRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService usrDtlSrv;

	@Autowired
	private JwtUtil jwtUtls;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		
		String jwtToken = null;
		String username = null; 
		/**
		 * Extract username from Jwt token
		 */
		if(authHeader!=null  && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			username = jwtUtls.extractUserName(jwtToken);
		}
		/**
		 * UsernamePasswordAuthenticationToken is default token which is used by spring security to manage authentication
		 * this is after validate JwtToken
		 * 
		 * stimulating normal flow of execution, which is by default spring security done.
		 */
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() ==  null) {
			UserDetails usrDetails = this.usrDtlSrv.loadUserByUsername(username);
			if(jwtUtls.validateToken(jwtToken, usrDetails)) {
				UsernamePasswordAuthenticationToken usrPassAuthToken = 
						new UsernamePasswordAuthenticationToken(usrDetails, null, usrDetails.getAuthorities());
				usrPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usrPassAuthToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
