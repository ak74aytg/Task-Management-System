package com.register.securityBuffer.Service;

import com.register.securityBuffer.Entity.Task;
import com.register.securityBuffer.Entity.User;
import com.register.securityBuffer.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;


    public List<Task> getUserTasks(User user, boolean isAdmin) {
        if(isAdmin){
            return taskRepository.findAll();
        }else{
            return taskRepository.findByAssignedTo(user);
        }
    }

    public void createTask(Task task, User user){
        if(user.getRole().equals("ADMIN")){
            taskRepository.save(task);
        }
    }

    public void deleteTask(Task task, User user){
        if(user.getRole().equals("ADMIN")){
            taskRepository.delete(task);
        }
    }

    public void completeTask(Long id, User user){
            Optional<Task> task = taskRepository.findById(id);
            if (task.isPresent()) {
                Task existingTask = task.get();
                existingTask.setIsCompleted(!existingTask.getIsCompleted());
                taskRepository.save(existingTask);
            }
    }

    public void updateTask(Task updatedTask, User loggedInUser) {
        if(loggedInUser.getRole().equals("ADMIN")){
            Optional<Task> optionalTask = taskRepository.findById(updatedTask.getId());
            if (optionalTask.isPresent()) {
                Task existingTask = optionalTask.get();
                existingTask.setTaskName(updatedTask.getTaskName());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setAssignedTo(updatedTask.getAssignedTo());
                existingTask.setIsCompleted(updatedTask.getIsCompleted());
                taskRepository.save(existingTask);
            }
        }
    }
}
