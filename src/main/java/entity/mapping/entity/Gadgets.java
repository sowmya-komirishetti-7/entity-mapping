package entity.mapping.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

/**
 * The Gadgets class represents a gadget entity with a unique ID, name, and the 
 * number of years of usage or ownership. It generates a unique ID automatically 
 * when a new gadget is created.
 * 
 * This entity is used to store gadget information in the system.
 * 
 * @author Sowmya
 */
@Entity
public class Gadgets {

	/**
	 * The unique identifier for the gadget. It is automatically generated 
	 * using a UUID before the entity is persisted.
	 */
	@Id
	private String gadget_id;

	/**
	 * The name of the gadget.
	 */
	private String gadget_name;

	/**
	 * The number of years the gadget has been owned or used.
	 */
	private Float years;

	/**
	 * Automatically generates a unique UUID for the gadget_id before persisting the entity.
	 */
	@PrePersist
	protected void onCreate() {
		this.gadget_id = UUID.randomUUID().toString();
	}

	/**
	 * Default constructor for Gadgets class.
	 */
	public Gadgets() {
		super();
	}

	/**
	 * Gets the unique ID of the gadget.
	 * 
	 * @return the gadget ID.
	 */
	public String getId() {
		return gadget_id;
	}

	/**
	 * Sets the unique ID for the gadget.
	 * 
	 * @param gadget_id the gadget ID to set.
	 */
	public void setId(String gadget_id) {
		this.gadget_id = gadget_id;
	}

	/**
	 * Gets the name of the gadget.
	 * 
	 * @return the gadget name.
	 */
	public String getGadget_name() {
		return gadget_name;
	}

	/**
	 * Sets the name of the gadget.
	 * 
	 * @param gadget_name the gadget name to set.
	 */
	public void setGadget_name(String gadget_name) {
		this.gadget_name = gadget_name;
	}

	/**
	 * Gets the number of years the gadget has been used or owned.
	 * 
	 * @return the years of usage.
	 */
	public Float getYears() {
		return years;
	}

	/**
	 * Sets the number of years the gadget has been used or owned.
	 * 
	 * @param years the years to set.
	 */
	public void setYears(Float years) {
		this.years = years;
	}


}
