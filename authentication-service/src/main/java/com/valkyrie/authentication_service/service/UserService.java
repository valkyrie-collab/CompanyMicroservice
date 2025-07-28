package com.valkyrie.authentication_service.service;

import com.valkyrie.authentication_service.config.TokenConfiguration;
import com.valkyrie.authentication_service.model.Store;
import com.valkyrie.authentication_service.model.User;
import com.valkyrie.authentication_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(12);

    private UserRepository repo;
    @Autowired
    private void setRepo(UserRepository repo) {this.repo = repo;}

    private AuthenticationManager authenticationManager;
    @Autowired
    private void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private TokenConfiguration config;
    @Autowired
    private void setConfig(TokenConfiguration config) {this.config = config;}

    public Store<String> signIn(User user) {
        user = user.setPassword(ENCODER.encode(user.getPassword()));

        try {
            repo.save(user);
            return Store.initialize(HttpStatus.ACCEPTED, "user sign-in is a success......");
        } catch (Exception e) {
            return Store.initialize(HttpStatus.BAD_REQUEST, "Database problem occurred....");
        }

    }

    public Store<String> logIn(User user) {
        String token = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            token = config.generateToken(user.getUsername());
        }

        return Store.initialize(HttpStatus.OK, token);
    }

    public Store<User> getUser(String username) {
        return Store.initialize(HttpStatus.OK, repo.findById(username).orElse(null));
    }
}
