package com.example.functional;

public interface GenericInterfaceExample<K1, T2> {

	public T2 findElements(K1 t1);
	
	public K1 findReverseElements(T2 t1);
	
}

class GenericInterfaceImplExample implements GenericInterfaceExample<String , Integer>{

	@Override
	public Integer findElements(String t1) {
		return null;
	}

	@Override
	public String findReverseElements(Integer t1) {
		return null;
	}
	
}