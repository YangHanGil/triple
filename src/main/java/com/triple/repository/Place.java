package com.triple.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.model.place.PlaceDto;

@Mapper
public interface Place {

	List<PlaceDto>  selectTplace();
}
