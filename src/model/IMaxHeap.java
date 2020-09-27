package model;

import java.util.List;

public interface IMaxHeap<T extends Comparable<T>>{

	public void heapify(int i);
	
	public void buildMaxHeap();
	
	public void insert(T element);
	
	public T extract();
	
	public T returnMaximum();
	
	public int parent(int i);
	
	public int left(int i);
	
	public int right(int i);
	
	public void increaseKey(int i,T key);
	
	public List<T> getList();
	
}
