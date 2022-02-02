package com.example.signupsignin.service;

import com.example.signupsignin.model.Person;
import com.example.signupsignin.repository.PersonRespository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "username %s not found";
    private final static String USER_EXIST = "username %s already exist";

    private PersonRespository personRespository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRespository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Person person) {
        String response = String.format(USER_EXIST, person.getUsername());
        Optional<Person> userExist = personRespository.findByEmail(person.getUsername());
        if (userExist.isPresent()) {
            throw new IllegalStateException(response);
        }
        String encodedPassword = bCryptPasswordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        personRespository.save(person);
        return "signed up successfully";

    }


}
