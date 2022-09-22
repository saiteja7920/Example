package com.example.projectdemo.controllers;

import com.example.projectdemo.models.Address;
import com.example.projectdemo.models.Status;
import com.example.projectdemo.models.UserTable;
import com.example.projectdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private String newPassword;

    @PostMapping("/addUser")
    public String createStudent(@RequestBody UserTable userTable) {
        userRepository.save(userTable);
        return "User is added Successfully";
    }

    @GetMapping("/getAllUsers")
    public List<UserTable> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/getUserBy/{id}")
    public Object getById(@PathVariable int id) {
        if (!userRepository.findById(id).equals(Optional.empty())) {
            return userRepository.findById(id);
        }
        return "User With Id Not Found";
    }

    @PutMapping("/updateUser/{id}")
    public String getById(@PathVariable int id, @RequestBody UserTable userTable) {
        if (!userRepository.findById(id).equals(Optional.empty())) {
            UserTable u = userRepository.getById(id);
            u.setName(userTable.getName());
            u.setEmail(userTable.getEmail());
            u.setUserName(userTable.getUserName());
            u.setPassword(userTable.getPassword());
            u.setStatus(userTable.getStatus());
            u.setUpdatedOn(userTable.getUpdatedOn());
            Collections.singletonList(userRepository.save(u));
            return "User updated Successfully";
        }
        return "User with Id Not Found";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteById(@PathVariable int id) {
        if (!userRepository.findById(id).equals(Optional.empty())) {
            userRepository.deleteById(id);
            return "Deleted Successfully";
        }
        return "Id Not Found";
    }

    @DeleteMapping("/softDeleteUser/{id}")
    public String softDeleteById(@PathVariable int id) {
        if (!userRepository.findById(id).equals(Optional.empty())) {
            UserTable u = userRepository.getById(id);
            u.setStatus(Status.INACTIVE);
            userRepository.save(u);
            return "User successfully inactivated";
        }
        return "Id Not Found";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader String userName, @RequestHeader String password) {
        List<UserTable> users = userRepository.findByUserName(userName);
        for (int j = 0; j < users.size(); j++) {
            if(users.get(j).getStatus().compareTo(Status.ACTIVE)==0) {
                if (users.get(j).getUserName().compareTo(userName) == 0 && users.get(j).getPassword().compareTo(password) == 0) {
                    return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Inactive User ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User login Failed", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestHeader String userName, @RequestHeader String password,@RequestHeader String newPassword,@RequestHeader String reEnterNewPassword) {
        UserTable users = userRepository.findByUserNameIgnoreCase(userName);
            if (users.getUserName().compareTo(userName) == 0 && users.getPassword().compareTo(password) == 0) {
               UserTable us = userRepository.findByUserNameIgnoreCase(userName);
               us.setPassword(newPassword);
               us.setPassword(reEnterNewPassword);
               if(newPassword.equals(reEnterNewPassword)){
                   userRepository.save(us);
                   return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
               }
                return new ResponseEntity<>("Password Mismatch ", HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }

}

//    @PostMapping("/changePassword")
//    public ResponseEntity<String> changePassword(@RequestHeader String userName, @RequestHeader String password,@RequestHeader String newPassword) {
//        UserTable users = userRepository.findByUserNameIgnoreCase(userName);
//        if (users.getUserName().compareTo(userName) == 0 && users.getPassword().compareTo(password) == 0) {
//            UserTable us = userRepository.findByUserNameIgnoreCase(userName);
//            us.setPassword(newPassword);
//            userRepository.save(us);
//            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
//    }