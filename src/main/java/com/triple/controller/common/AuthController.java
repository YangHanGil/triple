package com.triple.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.triple.model.review.ReviewDto;
import com.triple.service.review.ReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    HttpSession session;
	
	@Autowired
	ReviewService reviewService;

    
    //리뷰작성
    @PostMapping(value = "/insertTreview", produces ="application/json; charset=UTF-8")
	@ApiOperation(value = "리뷰 작성/수정/삭제 구분")
    @ResponseBody
    public Object insertTreview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
		String result = "";
		
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
		JsonObject obj =new JsonObject();
	    obj.addProperty("result", result);
		
		return result;
    }
}
