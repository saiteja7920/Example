package com.example.projectdemo.repositories;

import com.example.projectdemo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
