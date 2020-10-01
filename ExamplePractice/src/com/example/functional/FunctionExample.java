package com.example.functional;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class FunctionExample {
	
	public static void main(String[] args) {
	    System.out.println("Stream without terminal operation");
	    
	    Arrays.stream(new int[] { 1, 2, 3 })
	    .map(i -> {
	        System.out.println("doubling " + i);
	        return i * 2;
	    });

	    
//	    System.out.println("Stream with terminal operation");
//	        Arrays.stream(new int[] { 1, 2, 3 }).map(i -> {
//	            System.out.println("doubling " + i);
//	            return i * 2;
//	    }).sum();
	    
	    
	    Map<String, List<String>> people = new HashMap<>();
	    people.put("John", Arrays.asList("555-1123", "555-3389"));
	    people.put("Mary", Arrays.asList("555-2243", "555-5264"));
	    people.put("Steve", Arrays.asList("555-6654", "555-3242"));
	     
	    List<String> phones = people.values().stream()
	      .flatMap(Collection::stream)
	        .collect(Collectors.toList());
	    
	    System.out.println("phones...."+phones);
	    
	    String str = "kitten";
	    System.out.println(str.length());
	    int n =4;
		if(n>=0 && n<=str.length()-1 ){
			String s=str.substring(0,n)+str.substring(n+1,str.length());
			System.out.println("s.."+s);
			System.out.println(str.lastIndexOf("en"));
			
			 boolean ss = str.startsWith("kt");
			System.out.println(ss);
			String str1="software testing help";
			String[] token = str1.split(""); 
			System.out.println(token.length + "....."+ token[0]);
			for(int i=token.length-1; i>=0; i--)
	        {
	            System.out.print(token[i] + "");
	        }
		}
	}	    

	
	
	}

