package com.synchronia.letfolio.infrastructure.repository;
import com.synchronia.letfolio.domain.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
