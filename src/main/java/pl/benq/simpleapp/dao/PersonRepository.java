package pl.benq.simpleapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.benq.simpleapp.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}