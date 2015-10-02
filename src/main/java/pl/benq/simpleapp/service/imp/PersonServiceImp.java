package pl.benq.simpleapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.benq.simpleapp.dao.PersonRepository;
import pl.benq.simpleapp.dao.PhoneTypeRepository;
import pl.benq.simpleapp.model.Bill;
import pl.benq.simpleapp.model.MonthPowerUsage;
import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Phone;
import pl.benq.simpleapp.model.PhoneType;
import pl.benq.simpleapp.model.Suite;
import pl.benq.simpleapp.service.PersonService;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

	private static final Integer MAX_PER_PAGE = 3;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PhoneTypeRepository phoneTypeRepository;
	
	@Override
	public void persist(Person person) {
		personRepository.save(person);
	}

	@Override
	public void update(Person person) {
		personRepository.save(person);
	}

	@Override
	public Person find(long id) {
		return personRepository.findOne(id);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public void delete(Person person) {
		personRepository.delete(person);
	}

	@Override
	public Page<Person> getPersonPage(Integer currentPage) {
		PageRequest request = 
				new PageRequest(currentPage-1, MAX_PER_PAGE);		
		return personRepository.findAll(request);
	}

	@Override
	public List<MonthPowerUsage> getMonthPowerUsage(Long id) {
		return personRepository.getPersonMonthPowerUsages(id);
	}

	@Override
	public Person addPhoneToPerson(Long personId, Long phoneTypeId, String number) {
		Person person = personRepository.findOne(personId);
		PhoneType type = phoneTypeRepository.findOne(phoneTypeId);
		Phone phone = new Phone();
		phone.setNumber(number);
		phone.setOwner(person);
		phone.setPhoneType(type);
		person.getPhones().add(phone);
		personRepository.save(person);
		
		return person;
	}
	
	@Override
	public void addPowerUsageForPerson(Long personId,MonthPowerUsage powerUsage){
		Person person = personRepository.findOne(personId);
		person.getPowerUsage().add(powerUsage);
		powerUsage.setPerson(person);
		List<Bill> bills = personRepository.getPersonBills(person.getId());
		bills.add(generateBillForPerson(person, powerUsage));
		person.setBills(bills);
		personRepository.save(person);
	}
	
	private Bill generateBillForPerson(Person person,MonthPowerUsage powerUsage){
		Suite personSuite = person.getSuite();
		
		Bill personBill = new Bill();
		personBill.setAbonament(personSuite.getAbonament());
		personBill.setCostPerKWH(personSuite.getCostPerKWH());
		personBill.setFromDate(powerUsage.getFromDate());
		personBill.setToDate(powerUsage.getToDate());
		personBill.setIsPayed(false);
		personBill.setUsageKWh(powerUsage.getUsageKWh());
		personBill.setPerson(person);
		personBill.setSuite(personSuite);
		return personBill;
	}
}
