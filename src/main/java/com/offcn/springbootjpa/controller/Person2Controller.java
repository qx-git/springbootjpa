package com.offcn.springbootjpa.controller;

import com.offcn.springbootjpa.jpadao.PersonRepository;
import com.offcn.springbootjpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangjian
 * @email 13120082225@163.com
 * @date 2020/5/11
 */
@RestController
@RequestMapping("/customPerson")
public class Person2Controller {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("findByNameIs/{name}")
    public Person findByNameIs(@PathVariable String name) {
        return personRepository.findByNameIs(name);
    }

    @GetMapping("findByNameIsAndPassword/{name}/{password}")
    public Person findByNameIsAndPassword(@PathVariable String name,@PathVariable String password) {
        return personRepository.findByNameIsAndPassword(name, password);
    }

    @GetMapping("findByNameContaining/{name}")
    public List<Person> findByNameContaining(@PathVariable String name) {
        return personRepository.findByNameContaining(name);
    }

}
