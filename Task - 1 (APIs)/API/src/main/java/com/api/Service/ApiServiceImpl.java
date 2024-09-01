package com.api.Service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiServiceImpl implements ApiService {

	@Override
	public String returnString(String name) {
		return "Hi "+name;
	}

	@Override
	public String palindrome(String str) {
		// TODO Auto-generated method stub
		str = str.replaceAll(" ", "");
		if(str.isEmpty()) return "Error, String Can't be Null";
		int i=0, j=str.length()-1;
		str = str.toLowerCase();

		while(i<j) {
			if(str.charAt(i)==str.charAt(j)) {
				i++; j--;
			} else return "No";
		}
		return "Yes";
	}
}
