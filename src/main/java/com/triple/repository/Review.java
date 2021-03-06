package com.triple.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.model.review.ReviewDto;

@Mapper
public interface Review {

	List<ReviewDto> selectTreview();
	
	int selectTreviewCnt(String placeId);
	int selectTreviewUserCnt(ReviewDto reviewDto);
	
	boolean insertTreview(ReviewDto reviewDto);
	boolean updateTreview(ReviewDto reviewDto);
	boolean deleteTreview(ReviewDto reviewDto);
	
	String selectTreviewFphoto(ReviewDto reviewDto);
}
