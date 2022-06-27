package com.triple.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.model.review.ReviewDto;

@Mapper
public interface Review {

	List<ReviewDto> selectTreview();
	
	int selectTreviewCnt();
	
	boolean insertTreview();
}
