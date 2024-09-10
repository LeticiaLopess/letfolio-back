package com.synchronia.letfolio.presentation.resource;

import com.synchronia.letfolio.application.service.CourseService;
import com.synchronia.letfolio.domain.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseResource {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        List<Course> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        Course course = service.findById(id);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping
    public ResponseEntity<Course> insert(@RequestBody Course course) {
        course = service.insert(course);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        course = service.update(id, course);
        return ResponseEntity.ok().body(course);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
