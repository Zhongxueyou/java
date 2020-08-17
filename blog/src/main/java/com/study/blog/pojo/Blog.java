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
//博客实体类
public class Blog {
    private Long id;
    private String title;//标题
    private String content;//内容
    private String firstPicture;//首图
    private String description;
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation; //赞赏开启
    private boolean shareStatement;//版权开启
    private boolean commentabled;//评论开启
    private boolean published;//发布
    private boolean recommend;//推荐
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private Long typeid;
    private Long userid;
    private String tagid;
    private Type type;//多对一
    private List<Tag> tags=new ArrayList<>();//多对多
    private User user;//多对一
    private List<Comment> comments=new ArrayList<>();//一对多

    /*public void init(){
        this.tagIds=tagsToIds(this.getTags());
    }
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids=new StringBuffer();
            boolean flag=false;
            for (Tag tag:tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag=true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }*/
}
