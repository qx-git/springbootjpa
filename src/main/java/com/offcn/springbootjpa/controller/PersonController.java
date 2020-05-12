package com.offcn.springbootjpa.controller;



import com.offcn.springbootjpa.jpadao.PersonRepository;
import com.offcn.springbootjpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/")
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
    }


    @GetMapping("/")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    @DeleteMapping("/")
    public void deletePerson(@RequestParam Long id) {
        personRepository.deleteById(id);
    }

    @PutMapping("/")
    public void updatePerson(@RequestBody Person person) {
        personRepository.saveAndFlush(person);
    }


}
