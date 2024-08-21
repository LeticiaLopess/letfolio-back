package com.synchronia.letfolio.application.service;

import com.synchronia.letfolio.application.service.exception.DatabaseException;
import com.synchronia.letfolio.application.service.exception.ResourceNotFoundException;
import com.synchronia.letfolio.domain.entity.Course;
import com.synchronia.letfolio.infrastructure.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course findById(Long id) {
        Optional<Course> course = repository.findById(id);
        return course.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Course insert(Course course) {
        return repository.save(course);
    }

    public void delete(Long id) {
        try {
            if(!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Course update(Long id, Course course) {
        try {
            Course entity = repository.getReferenceById(id);
            updateData(entity, course);
            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Course entity, Course course) {
        updateIfPresent(entity::setTitle, course.getTitle());
        updateIfPresent(entity::setDescription, course.getDescription());
        updateIfPresent(entity::setCompletionDate, course.getCompletionDate());
    }

    private <T> void updateIfPresent(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
