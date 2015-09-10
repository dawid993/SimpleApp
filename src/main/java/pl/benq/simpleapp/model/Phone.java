package pl.benq.simpleapp.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "phone_type", nullable = false, length = 20)
	private String type;

	@Basic
	@Column(name = "phone_number", nullable = false, length = 30)
	private String number;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "person_id")
	private Person owner;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
}
