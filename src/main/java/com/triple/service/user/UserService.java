package com.triple.service.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.triple.repository.Identify;
import com.triple.model.user.Login;
import com.triple.model.user.User;

@Service
public class UserService {

    @Autowired
    Identify identify;

    @Autowired
    HttpSession session;

	public String userLogin(Login login) {
        if (login.getF_id() == null || login.getF_id().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = identify.userLogin(login);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setF_pass(null);
        session.setAttribute("userinfo", user);
        return "로그인 성공";
    }
}
