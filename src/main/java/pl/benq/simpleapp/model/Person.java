package pl.benq.simpleapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = Person.SEQUENCE_NAME, sequenceName = Person.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
public class Person {

	public static final String SEQUENCE_NAME = "PERSON_SEQUENCE";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Person.SEQUENCE_NAME)
	@Column(name = "person_id", nullable = false)
	private long id;

	@Column(name = "person_name", nullable = false, length = 30)
	private String name;

	@Column(name = "person_surname", nullable = false, length = 60)
	private String surname;

	@OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<Phone> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}
