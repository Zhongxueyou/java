package com.study.blog.service;

import com.study.blog.pojo.Blog;
import com.study.blog.pojo.TypeQuery;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> getBlogPage(int currentPage, int pageSize);
    List<Blog> getBlogPage1(String title,boolean recommend,int currentPage, int pageSize,long typeId);
    List<Blog> getBlogPage2(String query,int currentPage, int pageSize);  //首页全局查询
    List<Blog> getBlogPage3(TypeQuery typeQuery, int start, int pageSize);
    List<Blog> getBlogPage4(String tagid);  //首页全局查询
    int count();
    void deleteBlog(Long id);
    int addBlog(Blog blog);
    Blog getBlog(Long id);
    int updateBlog(Blog blog);
    List<Blog> listRecommendBlogTOP(Integer size);  //推荐最新的前size博客
    int querycount(String query);  //博客总条数
    int querycountById(Long typeid);  //类型id查询博客总条数
    Blog getAndConvert(Long id);
    Map<String,List<Blog>> archiveBlog();
}
