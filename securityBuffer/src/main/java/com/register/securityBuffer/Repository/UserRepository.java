package com.register.securityBuffer.Repository;

import com.register.securityBuffer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

//    Optional<User> findFirstByEmailAndPassword(String email, String password);
}
