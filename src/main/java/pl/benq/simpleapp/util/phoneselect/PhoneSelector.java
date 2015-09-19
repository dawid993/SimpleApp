package pl.benq.simpleapp.util.phoneselect;

import java.util.ArrayList;
import java.util.List;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.model.PhoneXLSDescriptor;

public class PhoneSelector {
	public static final String MOBILE_TYPE = "Mobile";
	
	List<Person> persons;
	List<PhoneXLSDescriptor> selectedPhones;

	public PhoneSelector(List<Person> persons) {
		this.persons = persons;
		this.selectedPhones = new ArrayList<PhoneXLSDescriptor>();
	}

	public List<PhoneXLSDescriptor> selectPhones() {
		for (Person person : persons) {
			if (person.getPhones().size() > 0) 
				findPhone(person.getPhones());
		}
		return selectedPhones;
	}

	private void findPhone(List<Phone> phones) {
		Phone lastVerifiedPhone = null;
		PhoneXLSDescriptor xmlPhoneDesc = null;
		
		for (Phone phone : phones) {
			if(phone.getPhoneType().getPhoneType().equals(PhoneSelector.MOBILE_TYPE)){
				xmlPhoneDesc = getPhoneXLSDescription(phone);
				selectedPhones.add(xmlPhoneDesc);
				return;
			}
			else
				lastVerifiedPhone = phone;
		}
		
		xmlPhoneDesc = getPhoneXLSDescription(lastVerifiedPhone);
		selectedPhones.add(xmlPhoneDesc);
		
	}

	private PhoneXLSDescriptor getPhoneXLSDescription(Phone phone) {
		PhoneXLSDescriptor xmlPhoneDesc = new PhoneXLSDescriptor();
		xmlPhoneDesc.setOwnerName(phone.getOwner().getName());
		xmlPhoneDesc.setOnwerSurname(phone.getOwner().getSurname());
		xmlPhoneDesc.setNumber(phone.getNumber());
		xmlPhoneDesc.setType(phone.getPhoneType().getPhoneType());
		return xmlPhoneDesc;
	}
}
