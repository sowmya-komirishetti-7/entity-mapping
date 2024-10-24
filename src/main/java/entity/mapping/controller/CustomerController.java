package entity.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entity.mapping.entity.Customer;
import entity.mapping.service.CustomerService;

/**
 * The CustomerController class handles HTTP requests related to customers.
 * It provides endpoints to add, retrieve, update, and delete customer information.
 */
@RestController
public class CustomerController {

	/**
	 * The CustomerService instance used to handle business logic for customer operations.
	 * This service provides methods to add, retrieve, update, and delete customers.
	 */
	@Autowired
	private CustomerService service;

	/**
	 * Adds a new customer.
	 * 
	 * @param customer The customer object containing the information to add.
	 * @return A message indicating the result of the operation.
	 */
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}

	/**
	 * Retrieves all customers.
	 * 
	 * @return A list of all customers.
	 */
	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers() {
		return service.getAllCustomers();
	}

	/**
	 * Retrieves a customer by their ID.
	 * 
	 * @param id The ID of the customer to retrieve.
	 * @return The customer object with the specified ID.
	 */
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return service.getCustomerById(id);
	}

	/**
	 * Updates an existing customer.
	 * 
	 * @param id The ID of the customer to update.
	 * @param customer The customer object containing updated information.
	 * @return A response entity with a success message.
	 */
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable Integer id,@RequestBody Customer customer) {

		service.updateCustomer(id, customer);
		return ResponseEntity.ok("Data Saved");
	}

	/**
	 * Deletes a customer by their ID.
	 * 
	 * @param id The ID of the customer to delete.
	 * @return A message indicating the deletion result.
	 * @throws RuntimeException If the customer with the specified ID is not found.
	 */
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		//	        return service.deleteCustomer(id);

		Customer customer=service.getCustomerById(id);
		if(customer==null)
			throw new RuntimeException("Customer not found with that id ...deletion not possible");
		service.deleteCustomer(id);
		return "Deleted Customer id is "+id;

	}
}
