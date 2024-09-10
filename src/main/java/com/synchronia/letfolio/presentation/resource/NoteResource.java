package com.synchronia.letfolio.presentation.resource;

import com.synchronia.letfolio.application.service.NoteService;
import com.synchronia.letfolio.domain.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/notes")
public class NoteResource {

    @Autowired
    private NoteService service;

    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        List<Note> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        Note note = service.findById(id);
        return ResponseEntity.ok().body(note);
    }

    @PostMapping
    public ResponseEntity<Note> insert(@RequestBody Note note) {
        note = service.insert(note);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(note.getId()).toUri();
        return ResponseEntity.created(uri).body(note);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        note = service.update(id, note);
        return ResponseEntity.ok().body(note);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
