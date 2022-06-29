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

		//포토여부 포토가 없으면 1 있으면 0. -> 없으면 TOTAL값에서 빼기 위함
		int contentMilege = reviewDto.getAttachedPhotoIds()==null ? 1:0;
		reviewDto.setMilege(contentMilege);

		int totalMilege = milege.selectTmilegeFtotal(reviewDto);
		totalMilege -= contentMilege;
		reviewDto.setTotal(totalMilege);
		reviewDto.setStatus("P");
		if(contentMilege<0) milege.insertTmilege(reviewDto);
		
		
		result = review.updateTreview(reviewDto);
		
		return result;
	}
	
	public boolean deleteTreview(ReviewDto reviewDto) {
		boolean result = false;

		//저장된 리뷰의 마일리지 내역 가져옴
		MilegeDto milegeDto = milege.selectTmilegeToDelete(reviewDto);
		int contentMilege = milegeDto.getF_milege();
		reviewDto.setMilege(contentMilege);

		//본인이 작성한 리뷰의 마일리지를 총 마일리지 값에서 뺌.
		int totalMilege = milege.selectTmilegeFtotal(reviewDto);
		totalMilege -= contentMilege;
		reviewDto.setTotal(totalMilege);
		reviewDto.setStatus("M");
		
		milege.insertTmilege(reviewDto);
		
		result = review.deleteTreview(reviewDto);
		
		return result;
	}
}
