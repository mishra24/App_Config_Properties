package com.example.functional;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

//@FunctionalInterface
public interface Function2 {
     void  applyOne(int a);
 
    default void count(int a) {
        // increment counter
    }
    
}


