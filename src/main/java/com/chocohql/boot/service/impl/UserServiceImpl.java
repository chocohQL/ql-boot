package com.chocohql.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chocohql.boot.domain.entity.User;
import com.chocohql.boot.domain.dto.UserResp;
import com.chocohql.boot.exception.auth.PasswordErrorException;
import com.chocohql.boot.exception.auth.UsernameNotFountException;
import com.chocohql.boot.mapper.UserMapper;
import com.chocohql.boot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        if (user == null) {
            throw new UsernameNotFountException();
        }
        if (DigestUtil.bcryptCheck(password, user.getPassword())) {
            StpUtil.login(user.getId());
            return StpUtil.getTokenInfo().getTokenValue();
        } else {
            throw new PasswordErrorException();
        }
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public UserResp me() {
        return getUserResp(userMapper.selectById(StpUtil.getLoginIdAsLong()));
    }

    private UserResp getUserResp(User user) {
        UserResp userResp = new UserResp();
        BeanUtils.copyProperties(user, userResp);
        return userResp;
    }
}