package com.qilicoh.boot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String intro;
    private String role;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
