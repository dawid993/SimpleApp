package pl.benq.simpleapp.testrun;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.benq.simpleapp.service.PersonService;

public class DataBaseTestRun {
	public static void main(String[] args) throws IllegalStateException, SystemException, InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		PersonService service = (PersonService) context.getBean("personServiceImp");
		
		String var = "(\\d{3})(\\d{4}|\\d{6})";
		Pattern p = Pattern.compile(var);
		Matcher match = p.matcher("12344442");
		System.out.println(match.matches());
		}
		
}
