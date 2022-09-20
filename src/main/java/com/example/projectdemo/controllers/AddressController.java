package com.example.projectdemo.controllers;

import com.example.projectdemo.models.Address;
import com.example.projectdemo.models.AddressType;
import com.example.projectdemo.models.Student;
import com.example.projectdemo.repositories.AddressRepository;
import com.example.projectdemo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AddressController {

    @Autowired
    private AddressRepository repository;
    @Autowired
    private StudentRepository srepository;

    private Address address;

    @Autowired
    public AddressController(AddressRepository repository,StudentRepository srepository){
        this.repository = repository;
        this.srepository = srepository;
    }


    @GetMapping("/getAllAddress")
    public List<Address> getAll(){
        return repository.findAll();
    }

   @GetMapping("/getAddress/{Address_Id}")
   public Object getById(@PathVariable int Address_Id) {
       if (!repository.findById(Address_Id).equals(Optional.empty())) {
           return repository.findById(Address_Id);
       }
       return "Id Not Found";
   }

   @GetMapping("/getAddressByStudentId")
   public ResponseEntity<Address> getAllAddressByStudentId(@RequestHeader Integer id,@RequestHeader String addressType){
        if(!srepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Address> addresses = repository.findByStudentId(id);
        for (int i=0; i< addresses.size(); i++){
            if(addresses.get(i).getAddressType().toString().compareTo(addressType)==0){
                return new ResponseEntity<>(addresses.get(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //return new ResponseEntity<>(addresses, HttpStatus.OK);
   }


   @PostMapping("/createAddress")
    public String createAddress(@RequestBody Address address, @RequestHeader Integer Student_Id){
        address.setStudent(new Student(Student_Id));
       repository.save(address);
        return "Address added";
   }

    @PutMapping("/updateAddress")
    public String getById(@RequestBody Address address,@RequestHeader Integer id){
        if (!repository.findById(id).equals(Optional.empty())) {
            Address add = repository.getById(id);
            add.setStreet(address.getStreet());
            add.setCity(address.getCity());
            add.setState(address.getState());
            add.setCountry(address.getCountry());
            add.setPincode(address.getPincode());
            add.setAddressType(address.getAddressType());
            Collections.singletonList(repository.save(add));
            return "Updated Successfully";
        }
        return "Id Not Found";
    }

    @DeleteMapping("/deleteAddress/{id}")
    public String deleteById(@PathVariable int id){
        if(!repository.findById(id).equals(Optional.empty())){
            repository.deleteById(id);
            return "Deleted Successfully";
        }
        return "Id Not Found";
    }
}
