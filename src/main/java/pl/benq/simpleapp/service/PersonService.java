package pl.benq.simpleapp.service;

import java.util.List;

import pl.benq.simpleapp.model.Person;

public interface PersonService {
	public void persist(Person person);

	public void update(Person person);

	public Person find(long id);

	public List<Person> findAll();

	public void delete(Person person);

}
