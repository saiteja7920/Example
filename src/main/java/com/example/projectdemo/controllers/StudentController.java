package com.example.projectdemo.controllers;

import com.example.projectdemo.models.Student;
import com.example.projectdemo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository srepository;

    @PostMapping("/createStudent")
    public  String createStudent(@RequestBody Student student){
        srepository.save(student);
        return "Student registered";
    }

    @GetMapping("/getAll")
    public List<Student> getAll()
    {
        return srepository.findAll();
    }

    @GetMapping("/getBy/mobile")
    public ResponseEntity<List<Student>> getByMobile(@RequestParam String mobile) {
        if (!srepository.findByMobile(mobile).equals(Optional.empty())) {
            return new ResponseEntity<List<Student>>(srepository.findByMobile(mobile), HttpStatus.OK);
        }
        return  null;
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable int id) {
        if (!srepository.findById(id).equals(Optional.empty())) {
            return srepository.findById(id);
        }
        return "Id Not Found";
    }

    @PutMapping("/update/{id}")
    public String getById(@PathVariable int id,@RequestBody Student student) {
        if (!srepository.findById(id).equals(Optional.empty())) {
            Student stu = srepository.getById(id);
            stu.setName(student.getName());
            stu.setEmail(student.getEmail());
            stu.setBranch(student.getBranch());
            stu.setMobile(student.getMobile());
            stu.setAadharNum(student.getAadharNum());
            stu.setPanNum(student.getPanNum());
            stu.setStudentPhoto(student.getStudentPhoto());
            Collections.singletonList(srepository.save(stu));
            return "Updated Succesfully";
        }
        return "Id Not Found";
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteById(@PathVariable int id){
        if(!srepository.findById(id).equals(Optional.empty())){
            srepository.deleteById(id);
            return "Deleted Succesfully";
        }
        return "Id Not Found";
    }
}
