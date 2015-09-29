package pl.benq.simpleapp.testrun;


import java.util.List;

import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.benq.simpleapp.model.MonthPowerUsage;
import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.service.PersonService;
import pl.benq.simpleapp.util.billcalculate.BillCalculatorStrategy;

public class DataBaseTestRun {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
	PersonService service = (PersonService) context.getBean("personServiceImp");
	BillCalculatorStrategy billCalculator=(BillCalculatorStrategy) context.getBean("billCalculatorPLStrategy");
	public static void main(String[] args) throws IllegalStateException, SystemException, InterruptedException {
		DataBaseTestRun run = new DataBaseTestRun();
		
		Person person =run.service.find(1);
		List<MonthPowerUsage> usages = run.service.getMonthPowerUsage(person.getId());
		for(MonthPowerUsage usage:usages){
			System.out.println(usage.getPerson().getName());
		}
	}
		
		
}
