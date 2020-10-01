package com.example.functional;

import java.util.Arrays;

//Normal class


public class GenericClassExample {
	
		public static void main(String args[]) {
			
		String action[] = new String[] {"apple", "orange", "banana","orange","papaya","orange"};		
		GenericTest<String> gint = new GenericTest<>();
		
		
		Integer[] intarr =  {1,2, 4, 2, 3};
		GenericTest<Integer> garr = new GenericTest<>();
		
				
		int y=GenericTest.countAllOccurrences(action, "orange");
		System.out.println("calling generic method for string  type.." + y);
		
		int fromint = garr.countAllOccurrences(intarr, 3);
		System.out.println("calling generic method for int type.." + fromint);
		
		gint.set(String.valueOf(10));
		
		String x = gint.get();
		
		System.out.println("writing generi example with type T and passing integer......" + x);
		
	}
	
	
	
	
}




class WithoutGenric{
	Object t;
	
	public void set(Object t) {
		this.t = t;
		
	}
	
	public Object  get() {
		return t;
	}
	
}


class GenericTest<T>{
	
	T t;	
	
	public void set(T t) {
		
		
		this.t=t;
	}
	
	public T get() {
		return t;
	}
	
	public static <T> int countAllOccurrences(T[] list, T item) {
		   int count = 0;
		   if (item == null) {
		      for ( T listItem : list )
		         if (listItem == null)
		            count++;
		   }
		   else {
		      for ( T listItem : list )
		         if (item.equals(listItem))
		            count++;
		   }
		   return count;
		} 
}