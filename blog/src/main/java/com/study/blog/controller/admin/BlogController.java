package com.study.blog.controller.admin;

import com.study.blog.pojo.Blog;
import com.study.blog.pojo.Tag;
import com.study.blog.pojo.Type;
import com.study.blog.pojo.User;
import com.study.blog.service.BlogService;
import com.study.blog.service.TagService;
import com.study.blog.service.TypeService;
import com.study.blog.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/blogs")
    public String list(@RequestParam(defaultValue = "0") Integer page, Model model){
        System.out.println("进入page====>"+page);
        //保存当前页
        int currentPage=0;
        //接受当前页
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        Pages pages=new Pages();
        pages.setCurrentPage(currentPage);
        int count = blogService.count();
        pages.setTotalCount(count);
        List<Blog> listBlog = blogService.getBlogPage(pages.getCurrentPage(), pages.getPageSize());
        System.out.println("listBlog=======>"+listBlog);
        model.addAttribute("page",pages);
        model.addAttribute("listBlog",listBlog);
        List<Type> type = typeService.getType();
        System.out.println("type=====>"+type);
        model.addAttribute("listType",type);
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String list1(HttpServletRequest request,Model model){
        String title = request.getParameter("title");
        String recommend1 = request.getParameter("recommend");
        String typeId1=request.getParameter("typeId");
        String page1=request.getParameter("page");
        System.out.println("pages=====>"+page1);
        int page =Integer.parseInt(page1);
        //保存当前页
        int currentPage=0;
        //接受当前页
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        Pages pages=new Pages();
        pages.setCurrentPage(currentPage);
        int count = blogService.count();
        pages.setTotalCount(count);
        System.out.println("typeId1=====>"+typeId1);
        if(typeId1.equals("")){
            typeId1="0";
            System.out.println("typeId1=>"+typeId1);
        }
        long typeId=Long.parseLong(typeId1);
        System.out.println("typeId====>"+typeId);
        Boolean recommend=Boolean.parseBoolean(recommend1);
        System.out.println("title====>"+title);
        System.out.println("recommend====>"+recommend);
        List<Blog> blogPage = blogService.getBlogPage1(title, recommend, pages.getCurrentPage(), pages.getPageSize(),typeId);
        model.addAttribute("page",pages);
        model.addAttribute("listBlog",blogPage);
        System.out.println("blogPage1==>"+blogPage);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("tags",tagService.getTag());
        model.addAttribute("types",typeService.getType());
        return "admin/blogs-input";
    }
    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Type type = typeService.listType(blog.getType().getId());
        blog.setUserid(user.getId());
        blog.setType(type);
        blog.setTagid(blog.getTagid());
        System.out.println("type=======>"+type);
        blog.setTypeid(blog.getType().getId());
        List<Tag> tag = tagService.getListTag(blog.getTagid());
        blog.setTags(tag);
        System.out.println("tag=================>"+tag);
        System.out.println("blog=======>"+blog);
        if(blog.getId()==null){
            blogService.addBlog(blog);
        }else {
            blogService.updateBlog(blog);
        }
        return "redirect:/admin/blogs";
    }
    @GetMapping("blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tags",tagService.getTag());
        model.addAttribute("types",typeService.getType());
        Blog blog = blogService.getBlog(id);
        blog.setTags(tagService.getListTag(blog.getTagid()));
        blog.setType(typeService.listType(blog.getTypeid()));
        System.out.println("要修改的blog=====>"+blog);
        model.addAttribute("blog",blog);
        return "/admin/blogs-input";
    }
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
