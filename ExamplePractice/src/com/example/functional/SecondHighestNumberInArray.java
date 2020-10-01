package com.example.functional;

public class SecondHighestNumberInArray {
	public static void main(String[] args) {
		int[] a = {100, 20,14,67,8};
		
		int firstLargest=0;
		int secondLargest=0;
		int thirdLargest=0;
		System.out.println(a.length);
		for(int i=0; i<=a.length-1; i++) {
			if(a[i]>firstLargest) {
				secondLargest=firstLargest;
				firstLargest=a[i];
				
			}else if(a[i]>secondLargest) {				
				thirdLargest=secondLargest;
				secondLargest=a[i];
			}else if(a[i]>thirdLargest) {
				thirdLargest =a[i];
			}
		}
		
		
		System.out.println("first largest.."+ firstLargest);
		System.out.println("second largest.."+ secondLargest);
		System.out.println("third largest.."+ thirdLargest);
	}

}
