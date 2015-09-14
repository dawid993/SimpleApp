package pl.benq.simpleapp.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = Phone.SEQUENCE_NAME, sequenceName = Phone.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
@JsonIgnoreProperties("owner")
public class Phone {

	public static final String SEQUENCE_NAME = "PHONE_SEQUENCE";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Phone.SEQUENCE_NAME)
	@Column(name = "phone_id", nullable = false)
	private long id;
	
	@Basic
	@Column(name = "phone_number", nullable = false, length = 30)
	private String number;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "person_id")
	private Person owner;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="phone_type",referencedColumnName = "phone_type_id")
	private PhoneType phoneType;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person ower) {
		this.owner = ower;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public long getId() {
		return id;
	}
}
