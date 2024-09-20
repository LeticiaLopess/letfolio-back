package com.synchronia.letfolio.application.service;

import com.synchronia.letfolio.application.service.exception.DatabaseException;
import com.synchronia.letfolio.application.service.exception.ResourceNotFoundException;
import com.synchronia.letfolio.domain.entity.Address;
import com.synchronia.letfolio.infrastructure.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address findById(Long id) {
        Optional<Address> address = repository.findById(id);
        return address.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Address insert(Address address) {
        return repository.save(address);
    }

    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }

            repository.deleteById(id);

        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Address update(Long id, Address address) {
        try {
            Address entity = repository.getReferenceById(id);
            updateData(entity, address);

            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Address entity, Address address) {
        updateIfPresent(entity::setStreet, address.getStreet());
        updateIfPresent(entity::setNumber, address.getNumber());
        updateIfPresent(entity::setComplement, address.getComplement());
        updateIfPresent(entity::setNeighborhood, address.getNeighborhood());
        updateIfPresent(entity::setCity, address.getCity());
        updateIfPresent(entity::setUf, address.getUf());
        updateIfPresent(entity::setZipCode, address.getZipCode());
    }

    private void updateIfPresent(Consumer<String> setter, String value) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }
}
