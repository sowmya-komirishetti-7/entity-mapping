package entity.mapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The User class represents a user entity in the system. 
 * It contains the user's basic information such as ID, name, email, password, and role.
 * 
 * Each user has a unique ID, a unique email, and is assigned a specific role in the system.
 */
@Entity
public class User {

	/**
	 * This is the primary key.
	 * 
	 * The unique identifier for the user, automatically generated.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	/**
	 * The username of the user.
	 */
	private String userName;

	/**
	 * The email of the user, which must be unique.
	 */
	@Column(unique = true)
	private String email;

	/**
	 * The password for the user account.
	 */
	private String password;

	/**
	 * The role assigned to the user (e.g., admin, user).
	 */
	private String role;

	/**
	 * Gets the user ID.
	 * 
	 * @return the user ID.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId the user ID to set.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the username of the user.
	 * 
	 * @return the username.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the username of the user.
	 * 
	 * @param userName the username to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the email of the user.
	 * 
	 * @return the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 * 
	 * @param email the email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role assigned to the user.
	 * 
	 * @return the user's role.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role for the user.
	 * 
	 * @param role the role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}



}
