package com.example.functional;

import java.util.HashMap;

public class CountWords {
	
	public static void main(String arg[]) {
		
		String str = "this this is is now there will be will saket";
		int count=1;
		String[] str1 =  str.split(" ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i =0; i<str1.length;i++) {
			if(map.containsKey(str1[i])) {
				map.put(str1[i], count+1);
			}else {
				map.put(str1[i], 1);
			}
//			count=1;
		}
		 System.out.println(map);
		 
		 
		 
		 // for palindrome
		 
		 String palindrome = "Boob";
		 String reverse="";
		
		 
		for(int i=palindrome.length()-1; i>=0; i--) {
			reverse = reverse+palindrome.charAt(i);
		}
		
		if(palindrome.equalsIgnoreCase(reverse)) {
			System.out.println("String is palindrome.." );
		}else {
			System.out.println("String is not palindrome.." );
		}
	}

}
