package com.triple.service.review;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triple.model.milege.MilegeDto;
import com.triple.model.review.ReviewDto;
import com.triple.repository.Milege;
import com.triple.repository.Review;
import com.triple.utils.ObjectUtil;

@Service
public class ReviewService {

	@Autowired
	Milege milege;
	
	@Autowired
	Review review;
	
	
	@Autowired
    HttpSession session;
	
	public boolean insertTreview(ReviewDto reviewDto) {
		boolean result = false;

		int reviewCnt = review.selectTreviewCnt(reviewDto.getReviewId());
		int contentMilege=0;
		
		ObjectUtil objectUtil = new ObjectUtil();
		//글자수
		contentMilege = objectUtil.point(reviewDto.getContent());
		//첫리뷰 여부
		contentMilege += reviewCnt<1 ? 1:0;
		//포토 여부
		contentMilege += reviewDto.getAttachedPhotoIds()!=null ? 0:1;
		if(reviewDto.getAttachedPhotoIds()!=null) {
			String photoList = null;
			for (String photo : reviewDto.getAttachedPhotoIds()) {
				photoList+=photo+",";
			}
			reviewDto.setPhotoId(photoList);
		}
		System.out.println(contentMilege);

		//현재 마일리지
		int totalMilege = milege.selectTmilegeFtotal(reviewDto);
		System.out.println(totalMilege);
		//현재 마일리지 + 추가 마일리지
		totalMilege += contentMilege;
		reviewDto.setTotal(totalMilege);
		reviewDto.setStatus("P");
		
		reviewDto.setMilege(contentMilege);
		milege.insertTmilege(reviewDto);
		
		result = review.insertTreview(reviewDto);
		
		return result;
	}
	
	public boolean updateTreview(ReviewDto reviewDto) {
		boolean result = false;

		//포토여부 포토가 없으면 0 있으면 1. -> 없으면 TOTAL값에서 빼기 위함
		int contentMilege = 0;
		
		if(reviewDto.getAttachedPhotoIds()!=null) {
			String photoList = null;
			for (String photo : reviewDto.getAttachedPhotoIds()) {
				photoList+=photo+",";
			}
			reviewDto.setPhotoId(photoList);
		}
		
		String isPhoto = review.selectTreviewFphoto(reviewDto);
		System.out.println(isPhoto);
		if(isPhoto!=null) //있었는데
			contentMilege = reviewDto.getAttachedPhotoIds().size()>0 ? 0:-1;//있고/없고
		else//없었는데
			contentMilege = reviewDto.getAttachedPhotoIds().size()>0 ? 1:0;//있고/없고
		
		reviewDto.setMilege(contentMilege);

		int totalMilege = milege.selectTmilegeFtotal(reviewDto);
		totalMilege += contentMilege;
		reviewDto.setTotal(totalMilege);
		reviewDto.setStatus("P");
		if(contentMilege!=0) milege.insertTmilege(reviewDto);
		
		
		result = review.updateTreview(reviewDto);
		
		return result;
	}
	
	public boolean deleteTreview(ReviewDto reviewDto) {
		boolean result = false;

		//저장된 리뷰의 마일리지 내역 가져옴
		int reviewPoint = milege.selectTmilegeToDelete(reviewDto);
		int contentMilege = reviewPoint;
		reviewDto.setMilege(-contentMilege);

		//본인이 작성한 리뷰의 마일리지를 총 마일리지 값에서 뺌.
		int totalMilege = milege.selectTmilegeFtotal(reviewDto);
		if(totalMilege > 0) {
			totalMilege -= contentMilege;
			reviewDto.setTotal(totalMilege);
			reviewDto.setStatus("M");
			milege.insertTmilege(reviewDto);
		}
		
		
		result = review.deleteTreview(reviewDto);
		
		return result;
	}
}
