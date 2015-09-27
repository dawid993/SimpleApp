package pl.benq.simpleapp.util.phoneselect;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.model.PhoneViewDescriptor;

public class PhoneSelector {
	public static final String MOBILE_TYPE = "Mobile";

	private Predicates predicates;

	public PhoneSelector() {
		this.predicates = new Predicates();
	}

	public List<PhoneViewDescriptor> selectPhones(List<Person> persons) {
		List<PhoneViewDescriptor> selectedPhones = new ArrayList<PhoneViewDescriptor>();
		Predicate<Phone> mobilePredicate = predicates.getPredicate(Predicates.MOBILE_TYPE);

		for (Person person : persons) {
			if (person.getPhones().size() > 0)
				selectedPhones.add(findPhone(person.getPhones(), mobilePredicate));
		}
		return selectedPhones;
	}

	private PhoneViewDescriptor findPhone(List<Phone> phones, Predicate<Phone> predicate) {
		Phone selectedPhone;
		List<Phone> criteriaPhones = phones.stream().filter(predicate).collect(Collectors.toList());
		if (criteriaPhones.size() == 0)
			selectedPhone = phones.get(0);
		else
			selectedPhone = criteriaPhones.get(0);
		return getPhoneXLSDescription(selectedPhone);
	}

	private PhoneViewDescriptor getPhoneXLSDescription(Phone phone) {
		PhoneViewDescriptor xmlPhoneDesc = new PhoneViewDescriptor();
		xmlPhoneDesc.setOwnerName(phone.getOwner().getName());
		xmlPhoneDesc.setOnwerSurname(phone.getOwner().getSurname());
		xmlPhoneDesc.setNumber(phone.getNumber());
		xmlPhoneDesc.setType(phone.getPhoneType().getPhoneType());
		return xmlPhoneDesc;
	}
}
