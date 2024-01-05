package com.register.securityBuffer.Controller;

import com.register.securityBuffer.Entity.Task;
import com.register.securityBuffer.Entity.User;
import com.register.securityBuffer.Service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/user-tasks")
    public ResponseEntity<?> getUserTasks(HttpSession session){
        User loggedInUserId = (User) session.getAttribute("user");
        if(loggedInUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");
        List<Task> userTasks = taskService.getUserTasks(loggedInUserId, isAdmin);
        return ResponseEntity.ok(userTasks);
    }

    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody Task task, HttpSession session){
        User loggedInUser = (User) session.getAttribute("user");
        taskService.createTask(task, loggedInUser);
        return ResponseEntity.ok("Task created successfully");
    }

    @PostMapping("/update-task")
    public ResponseEntity<?> updateTask(@RequestBody Task task, HttpSession session){
        User loggedInUser = (User) session.getAttribute("user");
        taskService.updateTask(task, loggedInUser);
        return ResponseEntity.ok("Task updated successfully");
    }

    @PostMapping("/complete-task")
    public ResponseEntity<?> CompleteTask(@RequestBody Long id, HttpSession session){
        User loggedInUser = (User) session.getAttribute("user");
        taskService.completeTask(id, loggedInUser);
        return ResponseEntity.ok("Task completed successfully");
    }

    @PostMapping("/delete-task")
    public ResponseEntity<?> DeleteTask(@RequestBody Task task, HttpSession session){
        User loggedInUser = (User) session.getAttribute("user");
        taskService.deleteTask(task, loggedInUser);
        return ResponseEntity.ok("Task completed successfully");
    }

}







