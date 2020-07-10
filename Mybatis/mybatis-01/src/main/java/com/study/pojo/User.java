package com.study.pojo;

import lombok.*;

import java.util.Date;
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private int age;
    private Date birthday;
    private int rid;
    private Teacher teacher;


}
