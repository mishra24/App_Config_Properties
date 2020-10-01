package com.example.functional;

public class FindMissingPostive {

	public static void main(String[] args) {
		int[] a =  {1,2,4,5};
		
		int num =  14;
		
		 numToWords(num);
	}

	
	public static void numToWords(int n) {
		char[] ch = String.valueOf(n).toCharArray();
		int len = ch.length;
		String ans=" ";
		int k =0;
		int pow=10;
		for(int i=len; i>0; i--) {
			while(k<i) {
			pow*=pow;
			System.out.println(pow);
			k++;
			}
			
			System.out.println("i: "+i);		
		}
//		ans +=(10*len-1)*ch[len];
		double result = Math.pow(3, 4);
		System.out.println("result" + result);
		System.out.println(Math.min(7, 5));
		
	}
}
