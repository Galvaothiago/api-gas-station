package com.gasstation.api.controllers.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.auth.ERole;
import com.gasstation.api.model.auth.RefreshToken;
import com.gasstation.api.model.auth.Role;
import com.gasstation.api.model.auth.User;
import com.gasstation.api.payload.request.LoginRequest;
import com.gasstation.api.payload.request.SignupRequest;
import com.gasstation.api.payload.request.TokenRefreshRequest;
import com.gasstation.api.payload.response.JwtResponse;
import com.gasstation.api.payload.response.MessageResponse;
import com.gasstation.api.payload.response.TokenRefreshResponse;
import com.gasstation.api.payload.response.UserInfo;
import com.gasstation.api.repositories.auth.RoleRepository;
import com.gasstation.api.repositories.auth.UserRepository;
import com.gasstation.api.security.exceptions.TokenRefreshException;
import com.gasstation.api.security.jwt.JwtUtils;
import com.gasstation.api.security.services.RefreshTokenService;
import com.gasstation.api.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@PostMapping(path = "/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()), null);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	  @PostMapping("/refreshtoken")
	  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
	    String requestRefreshToken = request.getRefreshToken();

	    return refreshTokenService.findByToken(requestRefreshToken)
	        .map(refreshTokenService::verifyExpiration)
	        .map(RefreshToken::getUser)
	        .map(user -> {
	          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
	          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
	        })
	        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
	            "Refresh token is not in database!"));
	  }
	  
	  @GetMapping("/me")
	  public ResponseEntity<UserInfo> getUserInfo(HttpServletRequest request) {
		  try {
			  
			  String[] headerAuth = request.getHeader("Authorization").split(" ");
			  String token = headerAuth[1];
			  
			  Optional<User> user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token));
		
			  if(user.isPresent()) {
				  User userInfo = user.get();
				  
				  UserInfo u = new UserInfo(userInfo.getId(), 
						  userInfo.getUsername(), 
						  userInfo.getEmail(), 
						  userInfo.getRoles());
				  
				  return ResponseEntity.ok().body(u);
			  }				
				  
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					   
			  
		  } catch(ArrayIndexOutOfBoundsException e) {
			  logger.error(e.getMessage());
		  } catch(ExpiredJwtException e) {
			  logger.error(e.getMessage());
		  } 
		  
		  return ResponseEntity.notFound().build();
	  }

}
