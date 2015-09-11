package pl.benq.simpleapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.service.PersonService;

@Controller
public class PersonManagedController {

	private final String personsView = "users";
	@Autowired
	PersonService personService;

	@RequestMapping("/persons")
	public String welcome(Model model) {
		model.addAttribute("persons", personService.findAll());
		return personsView;
	}

	@RequestMapping(value="/personphones")
	public @ResponseBody List<Phone> getUserPhones(@RequestParam("id") int personId) {
		return personService.find(personId).getPhones();
	}

	@RequestMapping(value="/addPhone")
	public @ResponseBody List<Phone> addPhoneForPersonAndReturnPhones(@RequestParam Map<String,String> parameters) {
		System.out.println("dssadas");
		Phone phone = new Phone();
		phone.setNumber(parameters.get("number"));
		phone.setType(parameters.get("type"));
		long id = Long.parseLong(parameters.get("id"));
		Person person = personService.find(id);
		phone.setOwner(person);
		person.getPhones().add(phone);
		personService.update(person);
		return person.getPhones();
	}
}
