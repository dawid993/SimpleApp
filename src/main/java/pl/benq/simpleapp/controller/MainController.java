package pl.benq.simpleapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.service.PersonService;

@Controller
public class MainController {
	
	@Autowired
	PersonService personSevice;
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "welcome";
	}
	
	@RequestMapping("/person/{id}")
	public String showForm(@PathVariable("id") int id,Model model)
	{
		Person person = personSevice.find(1);
		model.addAttribute("person", person);
		model.addAttribute("ala", "sdasd");
		return "form";
	}
	@RequestMapping("/submitForm")
	public String submit(@ModelAttribute("person") Person person)
	{
		System.out.println(person.getPhones());
		return "form";
	}
}
