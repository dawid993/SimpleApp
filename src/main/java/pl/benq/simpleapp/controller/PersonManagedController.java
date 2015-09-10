package pl.benq.simpleapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.service.PersonService;

@Controller
public class PersonManagedController {
	
	private final String personsView = "users";
	@Autowired
	PersonService personSevice;
	
	@RequestMapping("/persons")
	public String welcome(Model model)
	{
		model.addAttribute("persons", personSevice.findAll());
		return personsView;
	}
	
	@RequestMapping("/personphones")
	public @ResponseBody List<Phone> getUserPhones(@RequestParam("id") int personId)
	{
		return personSevice.find(personId).getPhones();
	}
}
