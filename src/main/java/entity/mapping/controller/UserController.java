package entity.mapping.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.mapping.dto.UserDto;
import entity.mapping.entity.Customer;
import entity.mapping.entity.User;
import entity.mapping.repository.CustomerRepository;
import entity.mapping.repository.UserRepository;
import entity.mapping.service.UserService;

/**
 * UserController handles user-related operations such as registration and login.
 * It provides endpoints to manage user authentication.
 */
@RestController
@RequestMapping
public class UserController {

	/**
	 * Repository for accessing user data.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Password encoder for encoding user passwords.
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Repository for accessing customer data.
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Authentication manager for handling user authentication.
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Registers a new user in the system.
	 *
	 * @param user The user object containing user information.
	 * @return A success message indicating that the user has been registered.
	 */
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		userRepository.save(user);
		return "User Registered Successfully!";
	}

	/**
	 * Authenticates a user based on their login credentials.
	 *
	 * @param loginRequest The UserDto object containing email and password.
	 * @return A response entity containing a success message if authentication is successful.
	 */
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserDto loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return ResponseEntity.ok("Login successful for user: " + loginRequest.getEmail());
	}


}
