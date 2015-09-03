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
import javax.persistence.TableGenerator;

@Entity
public class Person {
	
	@Id
	@TableGenerator(name="id_generator",table="table_id_storage",pkColumnName="table_name"
		,valueColumnName="stored_id")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="id_generator")
	@Column(name="person_id",nullable=false)
	private long id;
	
	@Column(name="person_name",nullable=false,length=30)
	private String name;
	
	@Column(name="person_surname",nullable=false,length=60)
	private String surname;
	
	@OneToMany(mappedBy="owner",cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
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
