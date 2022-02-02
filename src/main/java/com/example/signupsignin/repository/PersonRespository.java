package com.example.signupsignin.repository;

import com.example.signupsignin.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true) // *
public interface PersonRespository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
}
