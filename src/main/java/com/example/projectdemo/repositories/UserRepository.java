package com.example.projectdemo.repositories;

import com.example.projectdemo.models.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserTable, Integer> {


    List<UserTable> findByUserName(String userName);
}
