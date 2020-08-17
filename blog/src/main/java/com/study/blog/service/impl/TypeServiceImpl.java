package com.study.blog.service.impl;

import com.study.blog.mapper.TypeMapper;
import com.study.blog.pojo.Type;
import com.study.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> getTypePage(int currentPage, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return typeMapper.getTypePage(start,pageSize);
    }

    @Override
    public int count() {
        return typeMapper.count();
    }
    @Transactional
    @Override
    public void addType(String name) {
        typeMapper.addType(name);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Override
    public List<Type> getType() {
        return typeMapper.getType();
    }

    @Override
    public Type listType(Long id) {
        return typeMapper.listType(id);
    }
}
