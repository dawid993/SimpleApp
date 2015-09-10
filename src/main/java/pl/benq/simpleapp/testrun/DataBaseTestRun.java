package pl.benq.simpleapp.testrun;


import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.service.PersonService;

public class DataBaseTestRun {
	public static void main(String[] args) throws IllegalStateException, SystemException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		PersonService service = (PersonService) context.getBean("personServiceImp");
		
		Person person = service.find(1);
		person.getPhones().remove(0);
		
		System.out.println(person.getPhones());
		service.update(person);
		
	}
		
}
