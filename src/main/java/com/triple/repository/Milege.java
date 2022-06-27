package com.triple.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.model.milege.MilegeDto;

@Mapper
public interface Milege {

	List<MilegeDto> selectTmilege();
	
	boolean insertTmilegeP();
	boolean insertTmilegeM();
}
