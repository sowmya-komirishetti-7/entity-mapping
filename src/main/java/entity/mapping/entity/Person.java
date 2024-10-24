package entity.mapping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

/**
 * The Person class represents a person entity with a unique ID, gender, and a 
 * one-to-one relationship with a Customer.
 * 
 * This class includes a mapped ID to associate the person with a specific 
 * customer using a primary key.
 */
@Entity
public class Person {

	/**
	 * The unique identifier for the person, mapped to the customer ID.
	 */
	@Id	
	@Column(name="cust_id")
	private Integer id;

	/**
	 * The gender of the person.
	 */
	private String gender;

	/**
	 * The customer associated with this person in a one-to-one relationship.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JsonBackReference
	@JoinColumn(name = "cust_id")
	private Customer customer;

	/**
	 * Returns the ID of the person.
	 * 
	 * @return the person ID.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the ID of the person.
	 * 
	 * @param id the person ID to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns the gender of the person.
	 * 
	 * @return the gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the person.
	 * 
	 * @param gender the gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the customer associated with this person.
	 * 
	 * @return the associated customer.
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer associated with this person.
	 * 
	 * @param customer the customer to set.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
