package com.offcn.springbootjpa.jpadao;

import com.offcn.springbootjpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepositorySql extends JpaRepository<Person,Long> {

    @Query("select p from Person p where p.name=:name")
     Person getPerson(@Param("name") String name);

    //用户登录验证
    @Query("select p from Person p where p.name=?1 and p.password=?2")
     Person login(@Param("name") String name,@Param("password") String password);

    //模糊查询用户名里面包含指定字符
    @Query("select p from Person p where p.name like %:name%")
    List<Person> getNamesLike(@Param("name") String name);

    //查询密码位数是5位数的全部用户,使用mysql原始sql语句进行查询
    @Query(value="select * from person where length(password)=5",nativeQuery=true)
     List<Person> getPasswordisFive();
}
