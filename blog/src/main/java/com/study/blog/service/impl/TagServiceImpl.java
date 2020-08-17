package com.study.blog.service.impl;

import com.study.blog.mapper.TagMapper;
import com.study.blog.pojo.Tag;
import com.study.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> getTagPage(int currentPage, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return tagMapper.getTagPage(start,pageSize);
    }

    @Override
    public int count() {
        return tagMapper.count();
    }
    @Transactional
    @Override
    public void addTag(String name) {
        tagMapper.addTag(name);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTag() {
        return tagMapper.getTag();
    }

    @Override
    public List<Tag> getListTag(String ids) {
        return tagMapper.getListTag(convertToList(ids));
    }
    private List<Long> convertToList(String ids){
        List<Long> list=new ArrayList<>();
        if(!"".equals(ids) && ids!=null){
            String[] idarray=ids.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
