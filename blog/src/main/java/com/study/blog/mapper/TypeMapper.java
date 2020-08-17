package com.study.blog.mapper;

import com.study.blog.pojo.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TypeMapper {
    @Select("select * from t_type limit #{start},#{pageSize}")
    List<Type> getTypePage(int start, int pageSize);
    @Select("select count(*) from t_type")
    int count();
    @Insert("insert into t_type(name) values(#{name})")
    void addType(String name);
    @Delete("delete from t_type where id=#{id}")
    void deleteType(Long id);
    //@Select("select * from t_type")
    List<Type> getType();
    @Select("select * from t_type where id=#{id}")
    Type listType(Long id);
}
