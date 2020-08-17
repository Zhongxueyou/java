package com.study.blog.service.impl;

import com.study.blog.mapper.BlogMapper;
import com.study.blog.pojo.Blog;
import com.study.blog.pojo.Type;
import com.study.blog.pojo.TypeQuery;
import com.study.blog.pojo.User;
import com.study.blog.service.BlogService;
import com.study.blog.service.TagService;
import com.study.blog.service.TypeService;
import com.study.blog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Override
    public List<Blog> getBlogPage(int currentPage, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return blogMapper.getBlogPage(start,pageSize);
    }

    @Override
    public List<Blog> getBlogPage1(String title, boolean recommend, int currentPage, int pageSize, long typeId) {
        int start=(currentPage-1)*pageSize;
        return blogMapper.getBlogPage1(title,recommend,start,pageSize,typeId);
    }

    @Override
    public List<Blog> getBlogPage2(String query, int currentPage, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return blogMapper.getBlogPage2(query,start,pageSize);
    }

    @Override
    public List<Blog> getBlogPage3(TypeQuery typeQuery, int currentPage, int pageSize) {
        int start=(currentPage-1)*pageSize;
        System.out.println(typeQuery);
        return blogMapper.getBlogPage3(typeQuery,start,pageSize);
    }

    @Override
    public List<Blog> getBlogPage4(String tagid) {
        return blogMapper.getBlogPage4(tagid);
    }

    @Override
    public int count() {
        return blogMapper.count();
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public int addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        System.out.println("业务中的blog=====>"+blog);
        return blogMapper.addBlog(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public List<Blog> listRecommendBlogTOP(Integer size) {
        System.out.println(size);
        return blogMapper.listRecommendBlogTOP(size);
    }

    @Override
    public int querycount(String query) {
        return blogMapper.querycount(query);
    }

    @Override
    public int querycountById(Long typeid) {
        return blogMapper.querycountById(typeid);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.getBlog(id);
        blog.setType(typeService.listType(blog.getTypeid()));
        blog.setTags(tagService.getListTag(blog.getTagid()));
        Blog b=new Blog();
        BeanUtils.copyProperties(blog,b);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        return b;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogMapper.findGroupYear();
        Map<String,List<Blog>> map=new LinkedHashMap<>();
        for (String year:years){
            map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }
}
