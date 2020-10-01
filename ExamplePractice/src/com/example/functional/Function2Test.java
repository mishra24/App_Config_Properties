package com.example.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author mnarsing
 *
 */

class Function2Test {
	public static void main(String args[]) {
		
		String str = null;
		String str1= "";
		String str2 = "myway";
		
		Function2 obj = (i)-> System.out.println("let see .." +i);
				
				obj.applyOne(5);
				
				ArrayList<Integer> arrL = new ArrayList<Integer>(); 
		        arrL.add(1); 
		        arrL.add(2); 
		        arrL.add(3); 
		        arrL.add(4); 
		
		        arrL.forEach(n->System.out.println(n));
		        arrL.forEach(System.out::println);
		        
		        Consumer<Integer> action = System.out::println;
		       IntStream stream = Arrays.stream(new int[]{1, 2, 3})
		        		  .filter(i -> {
		        			 if(i >= 2)
		        			    return true;
		        			 return false;
		        		  })
		        		  .map(i -> {
		        			  System.out.println("ivalue .."+ i);
		        			 return  i*3;
		        			  });
//		        		  .sum();
		        
		        System.out.println("sum value ..." );
		        
		     
		        
		        
	}
	

}
