package com.study.blog.service;

import com.study.blog.pojo.Tag;
import java.util.List;

public interface TagService {
    List<Tag> getTagPage(int currentPage, int pageSize);
    int count();
    void addTag(String name);
    void deleteTag(Long id);
    List<Tag> getTag();
    List<Tag> getListTag(String ids);
}
