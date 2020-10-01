package com.example.functional;

import java.util.Arrays;

public class CyclicRotationArray {

	public static void main(String[] args) {
		
		int[] a = {3,5,0,8,-1};
		solution (a);
		arrayAsegregate (a);
	}

	
	// cyclic rotation
	public static void solution(int[] a) {
		int len=a.length;
		int[] result = new int[len];
		int j=0;
		for(int i=0; i<=len-1;i++) {
			if(i==0) {
				result[i]=a[len-1];
			}
			else {
			 result[i]=a[j];
			j++;
			}
		}
		
		
		System.out.println("cyclic rotation ...."+Arrays.toString(result));
	}
	
	
	// segregate array
	
	public static void arrayAsegregate(int[] a) {
		
		int low =0, high=a.length-1;
		
		while(low<=high) {
			
			if(a[low]>a[high]) {
				swap(a, low, high);
				low++;
//				j--;
				
			}
		}
		
		
		
	}

//	{3,5,0,8,-1};
	private static void swap(int[] arr, int x, int y) {
			int tmp=arr[x];
			arr[x]=arr[y];
			arr[y]=tmp;
			System.out.println("checking the order..... " +Arrays.toString(arr));
	}
	
	
}
