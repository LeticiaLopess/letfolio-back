package com.synchronia.letfolio.infrastructure.config;

import com.synchronia.letfolio.domain.entity.Address;
import com.synchronia.letfolio.domain.entity.Course;
import com.synchronia.letfolio.domain.entity.Note;
import com.synchronia.letfolio.domain.entity.User;
import com.synchronia.letfolio.domain.enumeration.Role;
import com.synchronia.letfolio.infrastructure.repository.AddressRepository;
import com.synchronia.letfolio.infrastructure.repository.CourseRepository;
import com.synchronia.letfolio.infrastructure.repository.NoteRepository;
import com.synchronia.letfolio.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        courseRepository.deleteAll();
        addressRepository.deleteAll();
        noteRepository.deleteAll();

        Address address = new Address(null, "23510360", "Rua Paraíso", "123", null, "Stockbridge", "Hilly", "RJ", Instant.now());
        addressRepository.save(address);

        Course course = new Course(null, "Java", "Curso de Java", Instant.parse("2024-03-29T10:53:07Z"), Instant.now());
        courseRepository.save(course);

        User user = new User(null, "leticia", "1234", "Leticia Lopes","leticialopes236@gmail.com", "21979168064", Instant.parse("2000-06-29T10:53:07Z"), Instant.now(), address);
        user.addRole(Role.ADMIN);
        user.addCourse(course);

        userRepository.save(user);

        Note note = new Note(null, "Tipos de dados", "Conteúdo da nota de tipos de dados", course, null, Instant.now());
        noteRepository.save(note);
    }

}
