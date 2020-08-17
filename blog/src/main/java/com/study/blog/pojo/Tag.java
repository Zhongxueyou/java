package com.study.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//标签实体类
public class Tag {
    private Long id;
    private String name;
    private List<Blog> blogs=new ArrayList<>();//多对多
}
