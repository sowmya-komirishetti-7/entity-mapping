package entity.mapping.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import entity.mapping.entity.User;

/**
 * MyUserDetails is an implementation of the Spring Security {@link UserDetails} interface.
 * It represents a user's authentication information, including their email, password,
 * and granted authorities (roles).
 * 
 * This class is used to provide user-specific data to Spring Security during the authentication process.
 */
public class MyUserDetails implements UserDetails{

	/** The email of the user, used as the username for authentication. */
	private String email;

	/** A list of granted authorities (roles) assigned to the user. */
	private String password;

	/** A list of granted authorities (roles) assigned to the user. */
	private List<GrantedAuthority> authorities;

	/** The User entity associated with this MyUserDetails instance. */
	private User user;

	/**
	 * Constructs a MyUserDetails instance from a User entity.
	 * 
	 * @param user the User entity containing user information.
	 */
	public MyUserDetails(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.authorities = Stream.of(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	/**
	 * Returns the authorities granted to the user.
	 * 
	 * @return a collection of granted authorities.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.authorities;
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return the user's password.
	 */
	@Override
	public String getPassword() {

		return user.getPassword();
	}

	/**
	 * Returns the username (email) of the user.
	 * 
	 * @return the user's email.
	 */
	@Override
	public String getUsername() {

		return user.getEmail();
	}

	/**
	 * Indicates whether the user's account is expired.
	 * 
	 * @return false, indicating the account is expired.
	 */
	@Override
	public boolean isAccountNonExpired() {

		return false;
	}

	/**
	 * Indicates whether the user's account is locked.
	 * 
	 * @return false, indicating the account is locked.
	 */
	@Override
	public boolean isAccountNonLocked() {

		return false;
	}

	/**
	 * Indicates whether the user's credentials (password) are expired.
	 * 
	 * @return false, indicating the credentials are expired.
	 */
	@Override
	public boolean isCredentialsNonExpired() {

		return false;
	}

	/**
	 * Indicates whether the user is enabled or disabled.
	 * 
	 * @return false, indicating the user is disabled.
	 */
	@Override
	public boolean isEnabled() {

		return false;
	}

}
