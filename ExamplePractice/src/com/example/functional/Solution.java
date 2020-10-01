package com.example.functional;

import java.util.Arrays;

class Solution {
	public static void main(String args[]) {
		int number = 20;

		Solution counts = new Solution();
		int[] digits=counts.solution(number);
		System.out.println( "Array ....."+ Arrays.toString(digits));
	}

	public int[] solution(int N) {
		int i = 2;
		int[] a = new int[2];
		System.out.println(N / 2 + "and" + N % 2);
		while (i < N) {
			int breakcount = 0;
			int x = 0, y = 0, total = 0;
			if (String.valueOf(N / i).contains("0")) {
				x = N / i - 1;
				y = N - N / i + 1;
				total = x + y;
				System.out.println("yes number contain zero digit value of i :  " + i + " x  : " + x + " y :  " + y
						+ "  Total  : " + total);
				a[0] =x;
				a[1]=y;
			} else {
				if (N / i == 1) {
					i++;
					continue;
				}
				x = N / i;
				y = N - N / i;
				total = x + y;
				System.out.println("No  number deoes not contain zero digit  value of i :  " + i + " x  : " + x
						+ " y :  " + y + "  Total  : " + total);
				a[0] =x;
				a[1]=y;
			}
			i++;
			System.out.println("a[0],a[1]" +Arrays.toString(a));
		}
		return a;
	}
}