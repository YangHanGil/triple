package com.triple.repository;

import org.apache.ibatis.annotations.Mapper;

import com.triple.model.user.Login;
import com.triple.model.user.User;

@Mapper
public interface Identify {

	/*LOGIN INFO*/
    User login(Login login);
    
    /*USER LOGIN*/
    User userLogin(Login login);

}
