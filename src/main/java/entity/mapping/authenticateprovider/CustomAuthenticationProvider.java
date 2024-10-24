package entity.mapping.authenticateprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import entity.mapping.service.UserService;

/**
 * CustomAuthenticationProvider is an implementation of AuthenticationProvider 
 * that provides custom authentication logic for user login.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/**
	 * UserService is a service used to load user-specific data.
	 * This is autowired to allow Spring to inject an instance of UserService.
	 */
	@Autowired
	private UserService  userDetailsService;

	/**
	 * Authenticates a user based on the provided authentication details.
	 *
	 * @param authentication the authentication request containing the username and password.
	 * @return an Authentication object containing the authenticated user's details.
	 * @throws BadCredentialsException if the username is not found or the credentials are invalid.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

		}catch(UsernameNotFoundException e) {
			throw new BadCredentialsException("Invalid Credentials");
		}
	}

	/**
	 * Indicates whether this provider supports the specified authentication type.
	 *
	 * @param authentication the authentication type to check.
	 * @return true if the provider supports the specified authentication type; false otherwise.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}


}
