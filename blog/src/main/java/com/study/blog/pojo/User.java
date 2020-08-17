package com.study.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//用户实体类
public class User {
    private Long id;
    private String userName;//用户名
    private String password;//密码
    private String email;//邮箱
    private String avatar;//头像
    private Integer type;//类型
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private List<Blog> blogs=new ArrayList<>();//一对多
}
