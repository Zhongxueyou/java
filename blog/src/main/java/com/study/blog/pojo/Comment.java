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
//评论实体类
public class Comment {
    private Long id;
    private String nickName;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avatar;//头像
    private Date createTime;//创建时间
    private Blog blog;//多对一
    private List<Comment> replyComments=new ArrayList<>();//一个父评论可以有多个子评论
    private Comment parentComment; //一个子评论只能对应一个父评论
}
