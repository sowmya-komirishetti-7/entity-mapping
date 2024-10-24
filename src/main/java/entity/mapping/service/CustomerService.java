package entity.mapping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.mapping.entity.Customer;
import entity.mapping.entity.Gadgets;
import entity.mapping.entity.Person;
import entity.mapping.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * The CustomerService class provides services related to customer management, 
 * including adding, retrieving, updating, and deleting customers.
 * 
 * This class interacts with the CustomerRepository to perform database operations 
 * and ensure the integrity of customer data.
 */
@Service
public class CustomerService {

	/**
	 * Repository for performing CRUD operations on Customer entities.
	 * This field is automatically injected by Spring.
	 */
	@Autowired
	private CustomerRepository repo;

	private EntityManager entityManager;

	/**
	 * Adds a new customer to the database.
	 * 
	 * @param customer the Customer object to be added.
	 * @return a success message if the customer is inserted successfully, 
	 *         otherwise an error message.
	 */
	public String addCustomer(Customer customer)
	{
		try {
			Person person = customer.getPerson();
			person.setCustomer(customer);
			customer = repo.save(customer);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(customer !=null)
			return "Succesfully Inserted";
		else
			return "Something Error Occured";
	}

	/**
	 * Retrieves a list of all customers from the database.
	 * 
	 * @return a List of Customer objects.
	 */
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	/**
	 * Retrieves a customer by its ID.
	 * 
	 * @param id the ID of the customer to retrieve.
	 * @return the Customer object if found, or null if not found.
	 */
	public Customer getCustomerById(Integer id) {
		Optional<Customer> customerOpt = repo.findById(id);
		return customerOpt.orElse(null);
	}

	/**
	 * Updates an existing customer's information.
	 * 
	 * @param id the ID of the customer to update.
	 * @param updatedCustomer the Customer object containing updated information.
	 * @return the updated Customer object.
	 * @throws IllegalArgumentException if the provided ID is null.
	 * @throws EntityNotFoundException if no customer is found with the provided ID.
	 */
	public Customer updateCustomer(Integer id, Customer updatedCustomer) {
		if (id == null) {
			throw new IllegalArgumentException("Customer ID cannot be null.");
		}

		Optional<Customer> existingCustomer = repo.findById(id);

		if (existingCustomer.isPresent()) {
			Customer customer = existingCustomer.get();
			customer.setName(updatedCustomer.getName());
			customer.setPhonenumber(updatedCustomer.getPhonenumber());
			customer.setGadgets(updatedCustomer.getGadgets());
			Person existingPerson = customer.getPerson();
			if (existingPerson != null) {

				existingPerson.setGender((updatedCustomer.getPerson().getGender()));

			}

			return repo.save(customer);
		} else {
			throw new EntityNotFoundException("Customer not found with ID: " + id);
		}
	}

	/**
	 * Deletes a customer by its ID.
	 * 
	 * @param id the ID of the customer to delete.
	 * @return a message indicating whether the deletion was successful or if an error occurred.
	 */	 
	public String deleteCustomer(Integer id) {
		try {
			repo.deleteById(id);
			return "Customer deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred while deleting customer";
		}
	}



}
