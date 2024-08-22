package com.synchronia.letfolio.application.service;

import com.synchronia.letfolio.application.service.exception.DatabaseException;
import com.synchronia.letfolio.application.service.exception.ResourceNotFoundException;
import com.synchronia.letfolio.domain.entity.User;
import com.synchronia.letfolio.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User user) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, user);

            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User user) {
        updateIfPresent(entity::setName, user.getName());
        updateIfPresent(entity::setUsername, user.getUsername());
        updateIfPresent(entity::setPassword, user.getPassword());
        updateIfPresent(entity::setBirthDate, user.getBirthDate());
        updateIfPresent(entity::setPhoneNumber, user.getPhoneNumber());
        updateIfPresent(entity::setMail, user.getMail());
        updateIfPresent(entity::setAddress, user.getAddress());
    }

    private <T> void updateIfPresent(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
