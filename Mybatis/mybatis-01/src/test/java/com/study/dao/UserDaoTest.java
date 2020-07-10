package com.study.dao;

import com.study.pojo.Teacher;
import com.study.pojo.User;
import com.study.utils.IDutils;
import com.study.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UserDaoTest {
    static Logger logger=Logger.getLogger(UserDaoTest.class);

    @Test
    public void test(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        System.out.println("--------全部用户--------");
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        List<User> list=userDao.getUserList();
        for(User user:list){
            System.out.println(user);
        }
        System.out.println("--------单个用户--------");
        User user1=userDao.getUser(1);
        System.out.println(user1);

        System.out.println("--------全部老师--------");
        TeacherDao teacherDao=sqlSession.getMapper(TeacherDao.class);
        List<Teacher> list1=teacherDao.getTeacherList();
        for(Teacher teacher:list1){
            System.out.println(teacher);
        }

        System.out.println("--------多对一 用户+老师--------");
        List<User> list2=userDao.getUserTeacherList();
        for(User user:list2){
            System.out.println(user);
        }

        System.out.println("--------插入--------");
        User user=new User();
        user.setId(66);
        user.setName("qq");
        user.setPassword(IDutils.getId());
        user.setAge(99);
        user.setBirthday(new Date());
        user.setRid(2);
        int i=userDao.insert(user);


        System.out.println("--------全部用户--------");
        UserDao userDao9=sqlSession.getMapper(UserDao.class);
        List<User> list9=userDao.getUserList();
        for(User user9:list9){
            System.out.println(user9);
        }
        sqlSession.close();
    }

}
