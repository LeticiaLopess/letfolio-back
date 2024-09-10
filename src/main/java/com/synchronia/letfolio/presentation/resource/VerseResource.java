package com.synchronia.letfolio.presentation.resource;

import com.synchronia.letfolio.application.service.VerseService;
import com.synchronia.letfolio.domain.entity.Verse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/verses")
public class VerseResource {

    @Autowired
    private VerseService service;

    @GetMapping
    public ResponseEntity<List<Verse>> findAll() {
        List<Verse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Verse> findById(@PathVariable Long id) {
        Verse verse = service.findById(id);
        return ResponseEntity.ok().body(verse);
    }

    @PostMapping
    public ResponseEntity<Verse> insert(@RequestBody Verse verse) {
        verse = service.insert(verse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(verse.getId()).toUri();
        return ResponseEntity.created(uri).body(verse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Verse> update(@PathVariable Long id, @RequestBody Verse verse) {
        verse = service.update(id, verse);
        return ResponseEntity.ok().body(verse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
