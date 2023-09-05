package com.example.jwttest.controller;

import com.example.jwttest.dto.LoginRequestDto;
import com.example.jwttest.dto.SignupRequestDto;
import com.example.jwttest.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/signup")
    public String signup(@ModelAttribute SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "로그인 하세요";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e) {
           return "로그인 실패";
        }

        return "로그인 성공";
    }
}