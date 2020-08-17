package com.study.blog.mapper;

import com.study.blog.pojo.Tag;
import com.study.blog.pojo.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface TagMapper {
    @Select("select * from t_tag limit #{start},#{pageSize}")
    List<Tag> getTagPage(int start, int pageSize);
    @Select("select count(*) from t_tag")
    int count();
    @Insert("insert into t_tag(name) values(#{name})")
    void addTag(String name);
    @Delete("delete from t_tag where id=#{id}")
    void deleteTag(Long id);
    //@Select("select * from t_tag")
    List<Tag> getTag();

    List<Tag> getListTag(List<Long> list);
}
