package com.study.blog.mapper;

import com.study.blog.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from t_user where user_name=#{username} and password=#{password}")
    User findUser(String username, String password);
    @Select("select * from t_user where id=#{id}")
    User findUserBy(Long id);
}
