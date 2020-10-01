package com.example.functional;

import java.util.Arrays;

public class AllSortAlg {

	public static void main(String[] args) {
		
		// This is unsorted array
        Integer[] array = new Integer[] { 12, 13, 24, 10, 3, 6, 90, 70 };
 
        // Let's sort using quick sort
        quickSort( array, 0, array.length - 1 );
 
        // Verify sorted array
        System.out.println(Arrays.toString(array));
	}
	
	public static void quickSort(Integer[] arr, int low, int high) 
    {
        //check for empty or null array
        if (arr == null || arr.length == 0){
            return;
        }
         
        if (low >= high){
            return;
        }
 
        //Get the pivot element from the middle of the list
        int middle = low + (high - low) / 2;
        System.out.println("middle..."+ middle);
        int pivot = arr[middle];
        System.out.println("arr[middle]..."+ arr[middle]);
        // make left < pivot and right > pivot
        int i = low, j = high;
        System.out.println("value of before while i......."+i);
        while (i <= j) 
        {
            //Check until all values on left side array are lower than pivot
        	// { 12, 13, 24, 10, 3, 6, 90, 70 }
            while (arr[i] < pivot) 
            {
                i++;
                
                System.out.println("value of I...."+i);
            }
            //Check until all values on left side array are greater than pivot
            while (arr[j] > pivot) 
            {
            	System.out.println("value of before J......."+j);
                j--;
                System.out.println("value of after J......."+j);
            }
            //Now compare values from both side of lists to see if they need swapping 
            //After swapping move the iterator on both lists
            
            System.out.println("value of after i......."+i);
            if (i <= j) 
            {
                swap (arr, i, j);
                i++;
                j--;
            }
        }
       // Do same operation as above recursively to sort two sub arrays
       
        if (low < j){
        	 System.out.println("first time firts if sorting "+ Arrays.toString(arr) + "and  low: "+ low +" j  : "+ j + "i :"+i +"high :"+ high);
            quickSort(arr, low, j);
        }
       
        if (high > i){
        	 System.out.println("first time second if sorting "+ Arrays.toString(arr) + "and  low: "+ low +" j  : "+ j + "i :"+i +"high :"+ high);
            quickSort(arr, i, high);
        }
    }
	
	
	public static void swap (Integer array[], int x, int y)
    {
		System.out.println(">>>>"+x+">>>>>>"+y);
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
        System.out.println("checking the order..... " +Arrays.toString(array));
    }
}
