package com.synchronia.letfolio.infrastructure.repository;
import com.synchronia.letfolio.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
