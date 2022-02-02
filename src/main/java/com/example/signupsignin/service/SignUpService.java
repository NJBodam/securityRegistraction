package com.example.signupsignin.service;

import com.example.signupsignin.dto.SignUpRequest;
import com.example.signupsignin.model.Person;
import com.example.signupsignin.model.PersonRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignUpService {
    private final PersonService personService;

    public String signUp(SignUpRequest request) {
        /*boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid"); // find better ways of handling exceptions
        }*/
        return personService.signUpUser(
                new Person(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getUsername(),
                        request.getPassword(),
                        PersonRole.USER, false, true
                )
        );
    }
}
