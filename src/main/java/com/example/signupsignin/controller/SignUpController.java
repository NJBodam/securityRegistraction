package com.example.signupsignin.controller;

import com.example.signupsignin.dto.SignInDto;
import com.example.signupsignin.dto.SignUpRequest;
import com.example.signupsignin.service.PersonService;
import com.example.signupsignin.service.SignUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping(path = "api/v1/signup")
@AllArgsConstructor
public class SignUpController {

    private SignUpService signUpService;
    private AuthenticationManager authenticationManager;
    private PersonService personService;

    @PostMapping
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody SignInDto signInDto) throws Exception{
       /* Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));*/
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));

            System.out.println("I am in sign in"+authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword())));

        } catch (Exception e) {
            throw new Exception("incorrect username or password!");
        }
        final UserDetails person = personService.loadUserByUsername(signInDto.getUsername());
        /*final AuthResponse res = new AuthResponse();
        res.setToken(jwt);
        response.addHeader("Authorization", "Bearer " + jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);*/

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
}
