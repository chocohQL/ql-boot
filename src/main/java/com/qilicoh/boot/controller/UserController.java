package com.qilicoh.boot.controller;

import com.qilicoh.boot.domain.dto.AuthReq;
import com.qilicoh.boot.domain.model.Response;
import com.qilicoh.boot.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody @Validated AuthReq req) {
        return Response.success(userService.login(req.getUsername(), req.getPassword()));
    }

    @PostMapping("/logout")
    public Response logout() {
        userService.logout();
        return Response.success();
    }

    @GetMapping("/me")
    public Response me() {
        return Response.success(userService.me());
    }

}
