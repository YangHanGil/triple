package com.triple.controller.common;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.model.review.ReviewDto;
import com.triple.service.review.ReviewService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    HttpSession session;
	
	@Autowired
	ReviewService reviewService;

    
    //리뷰작성
    @PostMapping(value = "/insertTreview", produces ="application/json; charset=UTF-8")
    public boolean insertTreview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
		boolean result = false;
		
		System.out.println(request.getCharacterEncoding());
		System.out.println(reviewDto.getContent());
		
		switch (reviewDto.getAction()) {
		case "ADD":
			result = reviewService.insertTreview(reviewDto);
			break;
		case "MOD":
			result = reviewService.updateTreview(reviewDto);
			break;
		case "DELETE":
			result = reviewService.deleteTreview(reviewDto);
			break;
		}
		
		return result;
    }
}
