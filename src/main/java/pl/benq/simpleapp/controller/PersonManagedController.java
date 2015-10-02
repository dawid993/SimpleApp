package pl.benq.simpleapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.model.PhoneViewDescriptor;
import pl.benq.simpleapp.service.PersonService;
import pl.benq.simpleapp.service.PhoneTypeService;
import pl.benq.simpleapp.util.numberformat.AbstractNumberCreator;
import pl.benq.simpleapp.util.numberformat.AbstractNumberFormatter;
import pl.benq.simpleapp.util.numberformat.NumberFormatterCreator;
import pl.benq.simpleapp.util.phoneselect.PhoneSelector;

@Controller
public class PersonManagedController {

	private final String personsView = "users";

	@Autowired
	PersonService personService;

	@Autowired
	PhoneTypeService phoneTypeService;

	@RequestMapping("/persons/{page}")
	public String getPersonPage(@PathVariable("page") Integer pageNumber, Model model) {
		Page<Person> page = personService.getPersonPage(pageNumber);
		Integer currentPage = pageNumber;
		Integer beginPage = Math.max(1, currentPage - 5);
		Integer endPage = Math.min(beginPage + 10, page.getTotalPages());

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("begin", beginPage);
		model.addAttribute("end", endPage);
		model.addAttribute("persons", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("phoneTypes", phoneTypeService.findAll());

		return personsView;
	}

	@RequestMapping(value = "/personphones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Phone> getUserPhones(@RequestParam("id") int personId) {
		return personService.find(personId).getPhones();
	}

	@RequestMapping(value = "/addPhone", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Phone> addPhoneForPersonAndReturnPhones(@RequestParam Map<String, String> parameters) {
		long id = Long.parseLong(parameters.get("id"));
		long phoneTypeId = Long.parseLong(parameters.get("type"));
		String number = getFormattedNumber(parameters.get("number"));
		Person person = personService.addPhoneToPerson(id, phoneTypeId, number);

		return person.getPhones();
	}

	@RequestMapping("/download")
	public ModelAndView getXLS() {
		List<PhoneViewDescriptor> descriptors;
		PhoneSelector selector = new PhoneSelector();
		descriptors = selector.selectPhones(personService.findAll());
		return new ModelAndView("exelView", "phones", descriptors);
	}

	private String getFormattedNumber(String number) {
		AbstractNumberCreator creator = new NumberFormatterCreator();
		AbstractNumberFormatter formatter = creator.create(number);
		return formatter.getFormatedNumber();

	}
}
