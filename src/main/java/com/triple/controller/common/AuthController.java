package com.triple.controller.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.model.user.Login;
import com.triple.service.user.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    HttpSession session;

	@Autowired
	UserService userService;

    //로그인
    @PostMapping("/login")
    public Object userLogin(@RequestBody Login login) {
        //로그인 아이디 성공.
        return userService.userLogin(login);
    }
}
