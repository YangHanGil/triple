package com.triple.utils;

public class ObjectUtil {

	public int point(String str) {
		String review = str.replaceAll(" ", "").trim();
		int point = review.length();
		return point;
	}
}
