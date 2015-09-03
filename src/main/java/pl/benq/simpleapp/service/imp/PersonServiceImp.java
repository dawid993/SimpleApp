package pl.benq.simpleapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.benq.simpleapp.dao.PersonRepository;
import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.service.PersonService;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public void persist(Person person) {
		personRepository.persist(person);
	}

	public void update(Person person) {
		personRepository.update(person);
	}

	public Person find(long id) {
		return personRepository.find(id);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public void delete(Person person) {
		personRepository.delete(person);
	}
	
}
