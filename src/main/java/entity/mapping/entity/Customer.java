package entity.mapping.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Represents a customer entity in the system
 * 
 * @author Sowmya
 *
 */
@Entity
public class Customer {

	/**
	 * Represents the unique identifier for the entity.
	 *
	 * This field is automatically generated using the 
	 * identity strategy for primary key generation.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/**
	 * The name of the customer.
	 */
	private String name;

	/**
	 * The phone number of the customer.
	 */
	private String phonenumber;

	/**
	 * The person entity associated with this customer.
	 * This field is mapped by the "customer" field in the Person entity.
	 * Changes to the customer will also cascade to the associated person.
	 */
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JsonManagedReference
	private Person person;

	/**
	 * A list of gadgets associated with the customer.
	 * Each gadget is linked to the customer through the "customer_id" column.
	 * Changes to the customer will cascade to all associated gadgets.
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", referencedColumnName ="id", nullable=false)
	private List<Gadgets> gadgets;


	/**
	 * Constructor to initialize a Customer object.
	 * 
	 * @param id      The unique identifier for the customer.
	 * @param name    The name of the customer.
	 * @param person  The person associated with the customer.
	 * @param gadgets The list of gadgets owned by the customer.
	 */
	public Customer(Integer id, String name, Person person, List<Gadgets> gadgets) {
		super();
		this.id = id;
		this.name = name;
		this.person = person;
		this.gadgets = gadgets;
	}

	/**
	 * Gets the unique identifier of the customer.
	 *
	 * @return the customer's ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the customer.
	 *
	 * @param id the unique identifier to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name of the customer.
	 *
	 * @return the customer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the customer.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the person associated with the customer.
	 *
	 * @return the associated person entity
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Sets the person associated with the customer.
	 *
	 * @param person the person entity to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Gets the list of gadgets associated with the customer.
	 *
	 * @return the list of gadgets
	 */
	public List<Gadgets> getGadgets() {
		return gadgets;
	}

	/**
	 * Sets the list of gadgets associated with the customer.
	 *
	 * @param gadgets the list of gadgets to set
	 */
	public void setGadgets(List<Gadgets> gadgets) {
		this.gadgets = gadgets;
	}

	/**
	 * Gets the phone number of the customer.
	 *
	 * @return the customer's phone number
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * Sets the phone number of the customer.
	 *
	 * @param phoneNumber the phone number to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



}
