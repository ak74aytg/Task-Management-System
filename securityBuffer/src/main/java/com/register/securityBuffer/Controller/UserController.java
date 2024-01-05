package com.register.securityBuffer.Controller;

import com.register.securityBuffer.DTO.LoginRequest;
import com.register.securityBuffer.DTO.RegisterRequest;
import com.register.securityBuffer.Entity.Task;
import com.register.securityBuffer.Entity.User;
import com.register.securityBuffer.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session){
        User authenticatedUser = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if(authenticatedUser != null){
            session.setAttribute("user", authenticatedUser);
            if(authenticatedUser.getRole().equals("ADMIN")){
                session.setAttribute("isAdmin", true);
            }else{
                session.setAttribute("isAdmin", false);
            }
            return ResponseEntity.ok(authenticatedUser);
        }else{

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("isAdmin");
        return ResponseEntity.ok("logout successful");
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest, HttpSession session){
        if(userService.isEmailTaken(registerRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }

        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setName(registerRequest.getName());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setRole(registerRequest.getRole());


        userService.registerUser(newUser);

        session.setAttribute("user", newUser);
        if(newUser.getRole().equals("ADMIN")){
            session.setAttribute("isAdmin", true);
        }else{
            session.setAttribute("isAdmin", false);
        }

        return ResponseEntity.ok("User registered successfully");
    }


    @GetMapping("/all")
    public ResponseEntity<?> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
