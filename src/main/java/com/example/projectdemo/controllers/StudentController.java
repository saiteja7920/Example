package com.example.projectdemo.controllers;

import com.example.projectdemo.models.Student;
import com.example.projectdemo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping("/createStudent")
    public  String createStudent(@RequestBody Student student){
        repository.save(student);
        return "Student registered";
    }

    @GetMapping("/getAll")
    public List<Student> getAll(@RequestParam("Student_photo") MultipartFile Student_photo)
    {
        return repository.findAll();
    }


   /** @GetMapping("/getBy/mobile_num")
    public ResponseEntity<List<Student>> getByMobile_num(@RequestParam String mobile_num) {
        return new ResponseEntity<List<Student>>(repository.findByMobile_num(mobile_num), HttpStatus.OK);
    }*/

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable int id) {
        if (!repository.findById(id).equals(Optional.empty())) {
            return repository.findById(id);
        }
        return "Id Not Found";
    }

    @PutMapping("/update/{id}")
    public String getById(@PathVariable int id,@RequestBody Student student) {
        if (!repository.findById(id).equals(Optional.empty())) {
            Student stu = repository.getById(id);
            stu.setName(student.getName());
            stu.setEmail(student.getEmail());
            stu.setBranch(student.getBranch());
            stu.setMobile_num(student.getMobile_num());
            stu.setAadhar_num(student.getAadhar_num());
            stu.setPan_num(student.getPan_num());
            stu.setStudent_photo(student.getStudent_photo());
            Collections.singletonList(repository.save(stu));
            return "Updated Succesfully";
        }
        return "Id Not Found";
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteById(@PathVariable int id){
        if(!repository.findById(id).equals(Optional.empty())){
            repository.deleteById(id);
            return "Deleted Succesfully";
        }
        return "Id Not Found";
    }
}
