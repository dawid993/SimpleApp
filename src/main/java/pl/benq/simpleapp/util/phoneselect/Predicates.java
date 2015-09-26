package pl.benq.simpleapp.util.phoneselect;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import pl.benq.simpleapp.model.Phone;

public class Predicates {
	public static final String MOBILE_TYPE = "Mobile";

	private Map<String, Predicate<Phone>> predicates;

	public Predicates() {
		predicates = new HashMap<String, Predicate<Phone>>();
		initPredicates();
	}

	public Predicate<Phone> getPredicate(String predicateName) {
		return predicates.get(predicateName);
	}

	private void initPredicates() {
		Predicate<Phone> mobilePredicate = p -> p.getPhoneType().getPhoneType().equals(MOBILE_TYPE);
		predicates.put(MOBILE_TYPE, mobilePredicate);
	}

}
