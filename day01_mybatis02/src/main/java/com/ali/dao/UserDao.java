package com.ali.dao;

import com.ali.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findByID(int id);

    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    /**
     *根据id修改用户信息
     * @param use
     */
    void update(User use);
}
