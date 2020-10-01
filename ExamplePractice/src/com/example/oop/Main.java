package com.example.oop;

class Animal {
	   public void sound() {
	         System.out.println("Some sound");
	   }
	}
	 
	class Lion extends Animal {
	   public void sound1() {
	         System.out.println("Roar");
	   }
	}
	 
	class Main 
	{
	   public static void main(String[] args) 
	   {
	        //Parent class reference is pointing to a parent object
//	        Animal animal = new Animal(); 
//	        animal.sound(); //Some sound
	 
	        //Parent class reference is pointing to a child object
	        Animal animal = new Lion(); 
	        animal.sound(); //Roar
	   }
	}
