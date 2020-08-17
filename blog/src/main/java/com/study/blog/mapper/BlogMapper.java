package com.study.blog.mapper;

import com.study.blog.pojo.Blog;
import com.study.blog.pojo.TypeQuery;
import com.study.blog.util.Pages;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {
    List<Blog> getBlogPage(int start, int pageSize);  //分页查询博客
    List<Blog> getBlogPage1(String title,boolean recommend,int start, int pageSize,long typeId);  //通过某些条件分页查询博客
    List<Blog> getBlogPage3(TypeQuery typeQuery, int start, int pageSize);
    @Select("select count(*) from t_blog")
    int count();  //博客总条数
    int addBlog(Blog blog);  //添加博客
    @Select("select * from t_blog where id=#{id}")
    Blog getBlog(Long id);  //通过id获取某条博客
    int updateBlog(Blog blog); //修改博客
    void deleteBlog(Long id);  //删除博客
    @Select("select * from t_blog where recommend=true order by update_time DESC limit 0,#{size}")
    List<Blog> listRecommendBlogTOP(Integer size);  //推荐博客
    //@Select("select * from t_blog where title like #{query} or content like #{query} limit #{start},#{pageSize}")
    List<Blog> getBlogPage2(String query,int start, int pageSize);  //首页全局查询
    @Select("select count(*) from t_blog where title like #{query} or content like #{query}")
    int querycount(String query);  //博客总条数
    @Select("select count(*) from t_blog where typeid=#{typeid}")
    int querycountById(Long typeid);  //类型id查询博客总条数
    List<Blog> getBlogPage4(String tagid);
    @Select("select date_format(update_time,'%Y') as year from t_blog group by date_format(update_time,'%Y') order by year desc")
    List<String> findGroupYear();//获取年份
    @Select("select * from t_blog where date_format(update_time,'%Y')=#{year}")
    List<Blog> findByYear(String year); //通过年份获取博客

}
