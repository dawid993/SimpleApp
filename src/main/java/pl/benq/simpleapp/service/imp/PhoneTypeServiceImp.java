package pl.benq.simpleapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.benq.simpleapp.dao.PhoneTypeRepository;
import pl.benq.simpleapp.model.PhoneType;
import pl.benq.simpleapp.service.PhoneTypeService;

@Service
@Transactional
public class PhoneTypeServiceImp implements PhoneTypeService {

	@Autowired
	PhoneTypeRepository phoneTypeRepository;
	
	public void persist(PhoneType type) {
		phoneTypeRepository.save(type);
	}

	public void update(PhoneType type) {
		phoneTypeRepository.save(type);
	}

	public PhoneType find(long id) {
		return phoneTypeRepository.findOne(id);
	}

	public List<PhoneType> findAll() {
		return phoneTypeRepository.findAll();
	}

	public void delete(PhoneType person) {
		phoneTypeRepository.delete(person);
	}

}
