package com.ali.test;

import com.ali.dao.UserDao;
import com.ali.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class MybatisTest {
    private InputStream is;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao mapper;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("sqlconfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(is);
        session = factory.openSession();
        mapper = session.getMapper(UserDao.class);
    }
    @After
    public void end() throws IOException {
        //开启事务
        session.commit();
        //释放资源
        session.close();
        is.close();

    }
    @Test
    public void test1() throws IOException {

        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void test2() throws IOException {

        User user = mapper.findByID(45);
        System.out.println(user);
    }
    @Test
    public void test3() throws IOException {
        User user = new User();
        user.setUsername("李晨");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京");
        System.out.println("保存前："+user);
        mapper.add(user);
        System.out.println("保存后："+user);
    }
    @Test
    public void test4() throws IOException {
        User user = new User();
        user.setId(49);
        user.setAddress("东北村儿");
        mapper.update(user);
    }
}
