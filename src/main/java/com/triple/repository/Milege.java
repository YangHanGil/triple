package com.triple.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.model.milege.MilegeDto;
import com.triple.model.review.ReviewDto;

@Mapper
public interface Milege {

	List<MilegeDto> selectTmilege();
	
	MilegeDto selectTmilegeToDelete(ReviewDto reviewDto);
	
	boolean insertTmilege(ReviewDto reviewDto);
	int selectTmilegeFtotal(ReviewDto reviewDto);
}
