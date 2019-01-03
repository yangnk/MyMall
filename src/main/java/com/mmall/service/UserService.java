package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-03 上午10:53
 **/
public interface UserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 用户登出
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 用户校验
     * @param str
     * @param type
     * @return
     */
    ServerResponse checkValid(String str, String type);
}
