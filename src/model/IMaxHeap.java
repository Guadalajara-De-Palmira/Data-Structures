package model;

public interface IMaxHeap<T extends Comparable<T>>{

	public void heapify(int i);
	
	public void buildMaxHeap();
	
	public void insert(T element);
	
	public T extract();
	
	public T show();
	
}
