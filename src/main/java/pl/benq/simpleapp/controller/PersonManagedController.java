package pl.benq.simpleapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.model.PhoneType;
import pl.benq.simpleapp.service.PersonService;
import pl.benq.simpleapp.service.PhoneTypeService;

@Controller
public class PersonManagedController {

	private final String personsView = "users";
	@Autowired
	PersonService personService;
	
	@Autowired
	PhoneTypeService phoneTypeService;

	@RequestMapping("/persons/{page}")
	public String getPersonPage(@PathVariable("page") Integer pageNumber,Model model) {
		Page<Person> page = personService.getPersonPage(pageNumber);
		Integer currentPage = pageNumber;
		Integer beginPage = Math.max(1, currentPage-5);
		Integer endPage = Math.min(beginPage+10,page.getTotalPages());
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("begin", beginPage);
		model.addAttribute("end", endPage);
		model.addAttribute("persons", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		
		model.addAttribute("phoneTypes",phoneTypeService.findAll());
		
		return personsView;
	}

	@RequestMapping(value="/personphones")
	public @ResponseBody List<Phone> getUserPhones(@RequestParam("id") int personId) {
		return personService.find(personId).getPhones();
	}

	@RequestMapping(value="/addPhone")
	public @ResponseBody List<Phone> addPhoneForPersonAndReturnPhones(@RequestParam Map<String,String> parameters) {
		long id = Long.parseLong(parameters.get("id"));
		long phoneTypeId = Long.parseLong(parameters.get("type"));		
		
		Person person = personService.find(id);
		PhoneType type = phoneTypeService.find(phoneTypeId);
		
		Phone phone = new Phone();
		phone.setNumber(parameters.get("number"));
		phone.setOwner(person);
		phone.setPhoneType(type);
		person.getPhones().add(phone);
		personService.update(person);
		
		return person.getPhones();
	}
}
