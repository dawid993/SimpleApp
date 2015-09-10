package pl.benq.simpleapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
@SequenceGenerator(name = Person.SEQUENCE_NAME, sequenceName = Person.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)

@SecondaryTable(name = "person_details",pkJoinColumns=
		@PrimaryKeyJoinColumn(name="person_details_id",referencedColumnName="person_id"))
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
	
	@Column(name = "city",length=30,nullable=false,table="person_details")
	private String city;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "born_date",nullable = false,table="person_details")
	private Date bornDate;
	
	@Column(name="vip_status",nullable = true,table="person_details")
	private boolean vipStatus;	

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public boolean isVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(boolean vipStatus) {
		this.vipStatus = vipStatus;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}
