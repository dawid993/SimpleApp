package pl.benq.simpleapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pl.benq.simpleapp.dao.PersonRepository;
import pl.benq.simpleapp.model.Person;

@Repository
public class PersonRepositoryImp implements PersonRepository {
	@PersistenceContext
	EntityManager manager;

	public void persist(Person person) {
		manager.persist(person);
	}

	public void update(Person person) {
		manager.merge(person);
	}

	public Person find(long id) {
		return manager.find(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		return 	manager.createQuery("FROM Person as p").getResultList();
	}

	public void delete(Person person) {
		manager.remove(person);
	}

}
