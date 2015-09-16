package pl.benq.simpleapp.testrun;


import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.service.PersonService;

public class DataBaseTestRun {
	public static void main(String[] args) throws IllegalStateException, SystemException, InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		PersonService service = (PersonService) context.getBean("personServiceImp");
		
		Person p = service.find(1);
		p.getPhones().remove(0);
		service.persist(p);
		}
		
}
