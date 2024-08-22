package com.synchronia.letfolio.application.service;

import com.synchronia.letfolio.application.service.exception.DatabaseException;
import com.synchronia.letfolio.application.service.exception.ResourceNotFoundException;
import com.synchronia.letfolio.domain.entity.User;
import com.synchronia.letfolio.domain.entity.Verse;
import com.synchronia.letfolio.infrastructure.repository.VerseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class VerseService {

    @Autowired
    private VerseRepository repository;

    public List<Verse> findAll() {
        return repository.findAll();
    }

    public Verse findById(Long id) {
        Optional<Verse> verse = repository.findById(id);
        return verse.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Verse insert(Verse verse) {
        return repository.save(verse);
    }

    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Verse update(Long id, Verse verse) {
        try {

            Verse entity = repository.getReferenceById(id);
            updateData(entity, verse);
            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Verse entity, Verse verse) {
        updateIfPresent(entity::setBook, verse.getBook());
        updateIfPresent(entity::setChapter, verse.getChapter());
        updateIfPresent(entity::setNumber, verse.getNumber());
        updateIfPresent(entity::setText, verse.getText());
    }

    private <T> void updateIfPresent(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
