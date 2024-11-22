package com.qilicoh.boot.service;

import com.qilicoh.boot.domain.dto.UserResp;

public interface UserService {
    String login(String username, String password);

    void logout();

    UserResp me();
}
