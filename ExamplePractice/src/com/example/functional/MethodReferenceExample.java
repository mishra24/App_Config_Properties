package com.example.functional;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample {

	public static void main(String[] args) {
		
		
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
		list.forEach(MethodReferenceExample::print);
		
		list.forEach(num -> MethodReferenceExample.print(num));
		
		}

	
	public static void print(final int number) {
		System.out.println("I am printing: " + number);
	}
}


