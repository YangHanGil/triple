package com.triple.model.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

	String type;
	String action;
	String reviewId;
	String content; //내용
	String attachedPhotoIds;
	String userId;
	String placeId;
	String status;
	int milege;
	int total;
}
