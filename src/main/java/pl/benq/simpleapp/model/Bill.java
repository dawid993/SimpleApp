package pl.benq.simpleapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bills")
@SequenceGenerator(name=Bill.SEQUENCE_NAME,sequenceName=Bill.SEQUENCE_NAME,initialValue=1,allocationSize=1)
public class Bill {
	public static final String SEQUENCE_NAME = "INVOICE_SEQUENCE";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=Bill.SEQUENCE_NAME)
	@Column(name="invoice_id")
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="from_date",nullable=false)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="to_date",nullable=false)
	private Date toDate;
	
	@Column(name="usage_kwh",nullable=false)
	private Double usageKWh;
	
	@Column(name="cost_per_kwh",nullable=false)
	private Double costPerKWH;
	
	@Column(name="abonament",nullable=false)
	private Double abonament;
	
	@Column(name="is_payed",nullable=false)
	private Boolean isPayed;
	
	@ManyToOne
	@JoinColumn(name="person_id",referencedColumnName="person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="suite_id",referencedColumnName="suite_id")
	private Suite suite;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Double getUsageKWh() {
		return usageKWh;
	}

	public void setUsageKWh(Double usageKWh) {
		this.usageKWh = usageKWh;
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

	public Boolean getIsPayed() {
		return isPayed;
	}

	public void setIsPayed(Boolean isPayed) {
		this.isPayed = isPayed;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Suite getSuite() {
		return suite;
	}

	public void setSuite(Suite suite) {
		this.suite = suite;
	}
}
