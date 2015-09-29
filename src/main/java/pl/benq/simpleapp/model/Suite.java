package pl.benq.simpleapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "suite")
@SequenceGenerator(name=Suite.SEQUENCE_NAME,sequenceName=Suite.SEQUENCE_NAME,initialValue=1,allocationSize=1)
public class Suite {
	public static final String SEQUENCE_NAME = "SUITE_SEQUENCE";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=Suite.SEQUENCE_NAME)
	@Column(name="suite_id")
	private long id;
	
	@Column(name="suite_name",nullable=false,unique=true,length=30)
	private String suiteName;
	
	@Column(name="cost_per_kwh",nullable=false)
	private Double costPerKWH;
	
	@Column(name="abonament",nullable=false)
	private Double abonament;
	
	@OneToOne(mappedBy="suite")
	private Person person;
	
	@OneToMany(mappedBy="suite")
	private List<Bill> bills;

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public Double getCostPerKWH() {
		return costPerKWH;
	}

	public void setCostPerKWH(Double costPerKWH) {
		this.costPerKWH = costPerKWH;
	}

	public Double getAbonament() {
		return abonament;
	}

	public void setAbonament(Double abonament) {
		this.abonament = abonament;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
