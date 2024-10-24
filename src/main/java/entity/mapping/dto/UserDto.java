package entity.mapping.dto;

import lombok.Data;

/**
 * The UserDto class represents a Data Transfer Object for user login information.
 * It contains the user's email and password, which are used for authentication.
 */
@Data
public class UserDto {
	
	/**
     * The email of the user.
     */
	private String email;
	
	/**
     * The password of the user.
     */
	private String password;

	/**
     * Gets the email of the user.
     * 
     * @return the user's email.
     */
	public String getEmail() {
		return email;
	}

	/**
     * Sets the email of the user.
     * 
     * @param email the email to set for the user.
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Gets the password of the user.
     * 
     * @return the user's password.
     */
	public String getPassword() {
		return password;
	}

	/**
     * Sets the password of the user.
     * 
     * @param password the password to set for the user.
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
