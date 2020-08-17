package com.study.blog.service;

import com.study.blog.pojo.Type;

import java.util.List;

public interface TypeService {
    List<Type> getTypePage(int currentPage, int pageSize);
    int count();
    void addType(String name);
    void deleteType(Long id);
    List<Type> getType();
    Type listType(Long id);
}
