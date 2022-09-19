package com.example.projectdemo.repositories;

import com.example.projectdemo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByStudentId(Integer id);
    //List<Address> findByStudentId(int studentId);
}
