package com.study.blog.controller;

import com.study.blog.pojo.Blog;
import com.study.blog.pojo.Type;
import com.study.blog.pojo.TypeQuery;
import com.study.blog.service.BlogService;
import com.study.blog.service.TypeService;
import com.study.blog.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model, Pages pages, @RequestParam(defaultValue="0") Integer page){
        int currentPage=0;
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        pages.setCurrentPage(currentPage);
        List<Type> types = typeService.getType();
        //int size = types.get(0).getBlogs().size();
        //pages.setTotalCount(size);
        pages.setPageSize(2);
        if(id==-1){
            id=types.get(0).getId();
            int size = blogService.querycountById(id);
            pages.setTotalCount(size);
            System.out.println(id);
        }
        int size = blogService.querycountById(id);
        pages.setTotalCount(size);
        TypeQuery typeQuery=new TypeQuery();
        typeQuery.setTypeid(id);
        List<Blog> blogs = blogService.getBlogPage3(typeQuery, pages.getCurrentPage(), pages.getPageSize());
        System.out.println(types);
        model.addAttribute("pages",pages);
        model.addAttribute("size",size);
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
