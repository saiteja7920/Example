package com.example.projectdemo.repositories;

import com.example.projectdemo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //List<Student> findByMobile_num(String mobile_num);

}
