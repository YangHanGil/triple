package com.triple.utils;

public class Review {

	public int point(String str) {
		String review = str.replaceAll(" ", "").trim();
		int point = review.length();
		return point;
	}
}
