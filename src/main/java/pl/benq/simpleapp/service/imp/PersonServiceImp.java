package pl.benq.simpleapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.benq.simpleapp.dao.PersonRepository;
import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.service.PersonService;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

	private static final Integer MAX_PER_PAGE = 3;
	@Autowired
	PersonRepository personRepository;
	
	public void persist(Person person) {
		personRepository.save(person);
	}

	public void update(Person person) {
		personRepository.save(person);
	}

	public Person find(long id) {
		return personRepository.findOne(id);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public void delete(Person person) {
		personRepository.delete(person);
	}

	public Page<Person> getPersonPage(Integer currentPage) {
		PageRequest request = 
				new PageRequest(currentPage-1, MAX_PER_PAGE);		
		return personRepository.findAll(request);
	}
	
}
