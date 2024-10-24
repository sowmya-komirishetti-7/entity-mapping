package entity.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.mapping.entity.User;

/**
 * The UserRepository interface provides CRUD operations and database interaction methods
 * for the User entity. It extends JpaRepository to utilize standard JPA methods
 * for saving, finding, updating, and deleting User records.
 * 
 * In addition to the standard methods, it also provides a custom method to find a User by their email.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see entity.mapping.entity.User
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	/**
	 * Custom method to find a User by their email address.
	 * 
	 * @param email   The email of the User.
	 * @return the User entity if found, otherwise null
	 */
	User findByEmail(String email);
}
