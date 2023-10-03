package com.example.eloemocional.models;

import com.example.eloemocional.models.enums.UserProfile;
import lombok.Data;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public abstract class Person {

    protected Long id;
    protected String name;
    protected String cpf;
    protected String email;
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();
    protected LocalDate dateCreation = LocalDate.now();

    public Person(Long id, String name, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addUserProfile(UserProfile.PATIENT);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserProfile> getUserProfiles() {
        return profiles.stream()
                .map(UserProfile::toEnumProfile)
                .collect(Collectors.toSet());
    }

    public void addUserProfile(UserProfile profile) {
        this.profiles.add(profile.getCode());
    }


    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Person() {
        super();
        addUserProfile(UserProfile.PATIENT);

    }
}
