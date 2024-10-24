package entity.mapping.service;

import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import entity.mapping.entity.User;
import entity.mapping.repository.UserRepository;

/**
 * The UserService class implements the UserDetailsService interface from Spring Security. 
 * It provides a way to load user-specific data for authentication.
 * This service is responsible for retrieving user details based on their email.
 */
@Service
public class UserService implements UserDetailsService {
	
	/**
	 * The UserRepository instance used to access user data from the database.
	 * This repository provides methods to perform CRUD operations on User entities.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
     * Loads a user by their email address.
     *
     * @param email the email of the user to be loaded.
     * @return a UserDetails object containing the user's information.
     * @throws UsernameNotFoundException if the user is not found with the given email.
     */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(),
				Collections.singleton(() -> user.getRole()));
	}
	
	
	

}
