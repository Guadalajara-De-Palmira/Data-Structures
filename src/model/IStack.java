package model;

public interface IStack <T extends Comparable<T>> {

	
	public void push(T value);
	
	public boolean isEmpty();
	
	public T top();
	
	public T pop();
	
	public int size();
}
