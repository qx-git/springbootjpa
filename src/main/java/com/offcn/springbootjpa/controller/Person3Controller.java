package com.offcn.springbootjpa.controller;

import com.offcn.springbootjpa.jpadao.PersonRepository;
import com.offcn.springbootjpa.jpadao.PersonRepositorySql;
import com.offcn.springbootjpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person3")
public class Person3Controller {

    @Autowired
    private PersonRepositorySql personRepository;
    @Autowired
    private PersonRepository personRepository2;

    @GetMapping("getPerson/{name}")
    public Person getPerson(@PathVariable String name) {
        return personRepository.getPerson(name);
    }

    @GetMapping("login/{name}/{password}")
    public Person login(@PathVariable String name, @PathVariable String password) {
        return personRepository.login(name, password);
    }

    @GetMapping("getNamesLike/{name}")
    public List<Person> getNamesLike(@PathVariable String name) {
        return personRepository.getNamesLike(name);
    }

    @GetMapping("getPasswordisFive")
    public List<Person> getPasswordisFive() {
        return personRepository.getPasswordisFive();
    }


    @PutMapping(path="updateName/{id}/{name}")
    public int updateName(@PathVariable Long id,@PathVariable String name) {
        return personRepository2.UpdateName(id,name);
    }

    @DeleteMapping(path="deleteName/{name}")
    public int DeleteName(@PathVariable String name) {
        return personRepository2.DeleteName(name);
    }
    /**
     * 查询包含指定账号名称，按照id进行排序，可以指定排序规则：asc升序、desc降序
     * @param name
     * @return
     */
    @GetMapping("findByNameSort/{sort}/{name}")
    public List<Person> findByNameSort(@PathVariable String name,@PathVariable String sort) {

        return personRepository2.findByNameContaining(name, Sort.by(Sort.Direction.fromString(sort),"id"));
    }

    /**
     * 查询包含指定账号名称，设置游标开始位置、每页记录数，可以指定按id排序:要计算总记录数（耗费sql资源较大）
     * @param name
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("findByNamePage1/{page}/{size}/{sort}/{name}")
    public Page<Person> findByNamePage1(@PathVariable String name, @PathVariable int page, @PathVariable int size, @PathVariable String sort) {
        return personRepository2.findByNameContaining(name, PageRequest.of(page, size,Sort.by(Sort.Direction.fromString(sort),"id")));
    }


    /**
     * 查询包含指定账号名称，设置游标开始位置、每页记录数，可以指定按id排序
     * @param name
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("findByNamePage2/{page}/{size}/{sort}/{name}")
    public Slice<Person> findByNamePage2(@PathVariable String name, @PathVariable int page, @PathVariable int size, @PathVariable String sort) {
        return personRepository2.getByNameContaining(name,PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(sort),"id")));
    }
}
