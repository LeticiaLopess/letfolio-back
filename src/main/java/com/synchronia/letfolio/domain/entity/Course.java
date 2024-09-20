package com.synchronia.letfolio.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Note> notes;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant completionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant creationDate;

    public Course() {
        this.notes = new ArrayList<>();
    }

    public Course(Long id, String title, String description, Instant completionDate, Instant creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completionDate = completionDate;
        this.creationDate = creationDate;
        this.notes = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        if (!notes.contains(note)) {
            notes.add(note);
            note.setCourse(this);
        }
    }

    public void removeNote(Note note) {
        if (notes.contains(note)) {
            notes.remove(note);
            note.setCourse(null);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            user.getCourses().add(this);
        }
    }

    public void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            user.getCourses().remove(this);
        }
    }

    public Instant getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Instant completionDate) {
        this.completionDate = completionDate;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
