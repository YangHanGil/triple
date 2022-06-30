package com.triple.model.review;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

	String type;
	String action;
	String reviewId;
	String content; //내용
	List<String> attachedPhotoIds;
	String userId;
	String placeId;
	String status;
	String photoId;
	int milege;
	int total;
}
