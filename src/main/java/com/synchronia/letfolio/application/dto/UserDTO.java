package com.synchronia.letfolio.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synchronia.letfolio.domain.enumeration.Role;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class UserDTO implements Serializable {

    private String username;
    private String password;
    private String name;
    private String mail;
    private String phoneNumber;
    private String birthDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant creationDate;

    private AddressDTO address;
    private List<Role> roles;
    private List<CourseDTO> courses;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String name, String mail, String phoneNumber, String birthDate, Instant creationDate, AddressDTO address, List<Role> roles, List<CourseDTO> courses) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.creationDate = creationDate;
        this.address = address;
        this.roles = roles;
        this.courses = courses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

}
