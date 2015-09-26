package pl.benq.simpleapp.testrun;


import java.util.List;

import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.benq.simpleapp.model.PhoneXLSDescriptor;
import pl.benq.simpleapp.service.PersonService;
import pl.benq.simpleapp.util.phoneselect.PhoneSelector;

public class DataBaseTestRun {
	public static void main(String[] args) throws IllegalStateException, SystemException, InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		PersonService service = (PersonService) context.getBean("personServiceImp");
		
			
		List<PhoneXLSDescriptor> descriptors;
		PhoneSelector selector = new PhoneSelector();
		descriptors = selector.selectPhones(service.findAll());
		for(PhoneXLSDescriptor desc:descriptors)
			System.out.println(desc.getOwnerName()+" "+desc.getOnwerSurname()+" "+desc.getNumber()+" "+desc.getType());
		}
}
