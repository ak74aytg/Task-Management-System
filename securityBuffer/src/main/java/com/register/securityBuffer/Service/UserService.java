package com.register.securityBuffer.Service;

import com.register.securityBuffer.Entity.User;
import com.register.securityBuffer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User authenticateUser(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }



    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
