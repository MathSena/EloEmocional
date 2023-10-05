package com.example.eloemocional.repository;

import com.example.eloemocional.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByCpf(String cpf);

    Optional<Person> findByEmail(String email);
}
