package com.assessment.hsbc.authenticateController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.jwt.Utils.JwtUtil;
import com.assessment.hsbc.jwt.mode.AuthenticationRequest;
import com.assessment.hsbc.jwt.mode.AuthenticationResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/authenticate")
public class AutenticateController {

	private static final Logger log = LoggerFactory.getLogger(AutenticateController.class);

	@Autowired
	private AuthenticationManager auth;

	@Autowired
	private UserDetailsService usrService;

	@Autowired
	private JwtUtil jwtUtl;

	/**
	 * authenticate API endpoint return JWT in the payload - Accepts userID and
	 * Password - UsernamePasswordAuthenticationToken is a standard spring mvc uses
	 * - If Authentication fails their is an exception or else Generate JWT Token
	 * and - Returns JWT as response
	 */
	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest req) throws Exception {
		try {
			auth.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
			final UserDetails userDetails = usrService.loadUserByUsername(req.getUsername());
			final String generateJwtToken = jwtUtl.generateToken(userDetails);
			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(generateJwtToken),
					HttpStatus.OK);
		} catch (AuthenticationException e) {
			log.error("Incorrect username and password..,", e);
			return new ResponseEntity<String>("Incorrect username and password..,", HttpStatus.BAD_REQUEST);
		}
	}
}
