package com.study.blog.controller;

import com.study.blog.NotFoundException;
import com.study.blog.pojo.Blog;
import com.study.blog.pojo.Tag;
import com.study.blog.pojo.Type;
import com.study.blog.pojo.User;
import com.study.blog.service.BlogService;
import com.study.blog.service.TagService;
import com.study.blog.service.TypeService;
import com.study.blog.service.UserService;
import com.study.blog.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue="0") Integer page,Model model, Pages pages) {
        System.out.println("page=========>"+page);
        int currentPage=0;
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        pages.setCurrentPage(currentPage);
        int count = blogService.count();
        pages.setTotalCount(count);
        List<Blog> blogPage = blogService.getBlogPage(pages.getCurrentPage(), pages.getPageSize());
        System.out.println("blogPage============>"+blogPage);
        model.addAttribute("pages",pages);
        model.addAttribute("count",count);
        model.addAttribute("page",blogPage);
        List<Tag> tag = tagService.getTag();
        System.out.println("首页查询tag================>"+tag);
        model.addAttribute("tags",tag);
        List<Type> type = typeService.getType();
        System.out.println("首页查询type================>"+type);
        model.addAttribute("types",type);
        List<Blog> listRecommendBlogTOP = blogService.listRecommendBlogTOP(10);
        System.out.println("blogs============>"+listRecommendBlogTOP);
        model.addAttribute("listRecommendBlogTOP",listRecommendBlogTOP);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue="0") Integer page,
                         @RequestParam(required = false) String query, Model model, Pages pages){
        int currentPage=0;
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        pages.setCurrentPage(currentPage);
        System.out.println(query);
        int count = blogService.querycount("%"+query+"%");
        System.out.println(count);
        pages.setTotalCount(count);
        pages.setPageSize(3);
        List<Blog> blogPage2 = blogService.getBlogPage2("%" + query + "%", pages.getCurrentPage(), pages.getPageSize());
        model.addAttribute("pages",pages);
        model.addAttribute("count",count);
        model.addAttribute("query",query);
        model.addAttribute("blogPage2",blogPage2);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog andConvert = blogService.getAndConvert(id);
        User user = userService.findUserBy(andConvert.getUserid());
        andConvert.setUser(user);
        System.out.println("查看的博客===============>"+andConvert);
        model.addAttribute("blog",andConvert);
        return "blog";
    }
    @GetMapping("/footer/newblog")
    public String footer(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTOP(3));
        return "_fragments :: newblogList";
    }

}
