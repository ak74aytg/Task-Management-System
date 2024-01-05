package com.register.securityBuffer.Repository;

import com.register.securityBuffer.Entity.Task;
import com.register.securityBuffer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User assignedTo);
}
