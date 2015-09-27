package pl.benq.simpleapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="phone_type")
@SequenceGenerator(name = PhoneType.SEQUENCE_NAME,sequenceName= PhoneType.SEQUENCE_NAME,initialValue=1,allocationSize=1)
@JsonIgnoreProperties("phones")
public class PhoneType {
	
	public static final String SEQUENCE_NAME = "PHONE_TYPE_SEQUENCE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PhoneType.SEQUENCE_NAME)
	@Column(name = "phone_type_id", nullable = false)
	private long id;
	
	@Column(name="phone_type_value",nullable=false,length=20)
	private String phoneType;
	
	@OneToMany(mappedBy="phoneType")
	private List<Phone> phones;

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public long getId() {
		return id;
	}

}
