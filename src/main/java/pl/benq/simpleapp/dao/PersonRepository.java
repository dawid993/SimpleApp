package pl.benq.simpleapp.dao;

import java.util.List;

import pl.benq.simpleapp.model.Person;

public interface PersonRepository {
	public void persist(Person person);
	
	public void update(Person person);
	
	public Person find(long id);
	
	public List<Person> findAll();
	
	public void delete(Person person);

}
