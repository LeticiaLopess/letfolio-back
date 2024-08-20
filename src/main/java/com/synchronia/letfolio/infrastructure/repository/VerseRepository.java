package com.synchronia.letfolio.infrastructure.repository;
import com.synchronia.letfolio.domain.entity.Verse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerseRepository extends JpaRepository<Verse, Long> {

}
