package pl.benq.simpleapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.benq.simpleapp.model.PhoneType;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, Long> {}
