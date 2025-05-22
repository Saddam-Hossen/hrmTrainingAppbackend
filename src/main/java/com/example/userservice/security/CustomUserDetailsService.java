package com.example.userservice.security;

import com.example.userservice.model.Employee;
import com.example.userservice.model.Quiz_Student;
import com.example.userservice.repository.Quiz_StudentRepository;
import com.example.userservice.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private Quiz_StudentRepository registrationRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Quiz_Student> data = registrationRepository.findByIdNumber(username);

        if (data.isPresent()) {
            Quiz_Student gg = data.get();

            // Ensure you retrieve roles dynamically
            // For example, if roles are stored as a list in Employee:
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();


            authorities.add(new SimpleGrantedAuthority("SNVN"));

            return new User(username, gg.getPassword(), authorities);  // Returning user with roles
        }
        throw new UsernameNotFoundException("User not found");
    }

}

