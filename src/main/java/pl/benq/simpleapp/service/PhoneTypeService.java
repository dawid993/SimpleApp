package pl.benq.simpleapp.service;

import java.util.List;

import pl.benq.simpleapp.model.PhoneType;

public interface PhoneTypeService {
	public void persist(PhoneType type);

	public void update(PhoneType type);

	public PhoneType find(long id);

	public List<PhoneType> findAll();

	public void delete(PhoneType type);

}
