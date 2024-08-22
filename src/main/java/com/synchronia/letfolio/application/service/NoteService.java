package com.synchronia.letfolio.application.service;

import com.synchronia.letfolio.application.service.exception.DatabaseException;
import com.synchronia.letfolio.application.service.exception.ResourceNotFoundException;
import com.synchronia.letfolio.domain.entity.Note;
import com.synchronia.letfolio.infrastructure.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public List<Note> findAll() {
        return repository.findAll();
    }

    public Note findById(Long id) {
        Optional<Note> note = repository.findById(id);
        return note.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Note insert(Note note) {
        return repository.save(note);
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

    public Note update(Long id, Note note) {
        try {
            Note entity = repository.getReferenceById(id);
            updateData(entity, note);

            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Note entity, Note note) {
        updateIfPresent(entity::setTitle, note.getTitle());
        updateIfPresent(entity::setContent, note.getContent());
        updateIfPresent(entity::setCourse, note.getCourse());
        updateIfPresent(entity::setImage, note.getImage());
    }

    private <T> void updateIfPresent(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
