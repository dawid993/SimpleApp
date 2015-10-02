package pl.benq.simpleapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.benq.simpleapp.model.Bill;
import pl.benq.simpleapp.model.MonthPowerUsage;
import pl.benq.simpleapp.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	@Query("SELECT m FROM MonthPowerUsage m WHERE m.person.id=?1")
	public List<MonthPowerUsage> getPersonMonthPowerUsages(Long id);
	
	@Query("SELECT b FROM Bill b WHERE b.person.id=?1")
	public List<Bill> getPersonBills(Long id);
}