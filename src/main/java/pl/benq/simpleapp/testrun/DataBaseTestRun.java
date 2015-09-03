package pl.benq.simpleapp.testrun;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.service.PersonService;

public class DataBaseTestRun {
	public static void main(String[] args) throws IllegalStateException, SystemException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		PersonService service = (PersonService) context.getBean("personServiceImp");
		
		Person person = service.find(52L);
		
		Phone phone = new Phone();
		phone.setNumber("544 89 21");
		phone.setType("Mobile Phone");		
		phone.setOwner(person);
		
		List<Phone> phones = person.getPhones();
		phones.add(phone);
		person.setPhones(phones);	
		
		service.update(person);
	}
		
}
