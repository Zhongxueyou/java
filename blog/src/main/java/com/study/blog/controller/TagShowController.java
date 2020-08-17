package com.study.blog.controller;

import com.study.blog.pojo.Blog;
import com.study.blog.pojo.Tag;
import com.study.blog.pojo.Type;
import com.study.blog.pojo.TypeQuery;
import com.study.blog.service.BlogService;
import com.study.blog.service.TagService;
import com.study.blog.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model, Pages pages, @RequestParam(defaultValue="0") Integer page){
        List<Blog> blogs=null;
        int currentPage=0;
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        pages.setCurrentPage(currentPage);
        List<Tag> tags = tagService.getTag();
        pages.setPageSize(5);
        System.out.println("tags===========>"+tags);
        if(id==-1){
            id=tags.get(0).getId();
            String tagid = Long.toString(id);
            blogs = blogService.getBlogPage4("%" + tagid + "%");
            System.out.println(blogs);
        }else {
            String tagid = Long.toString(id);
            blogs = blogService.getBlogPage4("%" + tagid + "%");
        }
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId",id);
        model.addAttribute("blogs",blogs);
        return "tags";
    }
}
