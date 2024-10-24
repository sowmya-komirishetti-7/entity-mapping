package entity.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.mapping.entity.Customer;
import entity.mapping.entity.User;

/**
 * The CustomerRepository interface provides CRUD operations and database interaction methods
 * for the Customer entity. It extends JpaRepository to utilize standard JPA methods
 * for saving, finding, updating, and deleting Customer records.
 * 
 * This interface will automatically be implemented by Spring Data JPA at runtime,
 * providing all the basic database operations without the need to write any implementation code.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see entity.mapping.entity.Customer
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {



}
