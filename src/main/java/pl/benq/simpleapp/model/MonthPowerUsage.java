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
@Table(name="month_power_usage")
@SequenceGenerator(name=MonthPowerUsage.SEQUENCE_NAME,sequenceName=MonthPowerUsage.SEQUENCE_NAME,initialValue=1,allocationSize=1)
public class MonthPowerUsage {
	public static final String SEQUENCE_NAME = "MONTH_POWER_USAGE_SEQUENCE";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=SEQUENCE_NAME)
	@Column(name="month_power_usage_id")
	private long id;
	
	@Column(name="usage_kwh",nullable=false)
	private Double usageKWh;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date",nullable=false)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="to_date",nullable=false)
	private Date toDate;
	
	@ManyToOne
	@JoinColumn(name="person_id",referencedColumnName="person_id")
	private Person person;

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
	
	public Double getUsageKWh() {
		return usageKWh;
	}

	public void setUsageKWh(Double usageKWh) {
		this.usageKWh = usageKWh;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}	
}
