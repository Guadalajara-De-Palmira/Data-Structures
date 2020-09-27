package model;

public interface IPriorityQueue<T extends Comparable<T>> {
	
	void insert(T newElement);
	
	T maximum();
	
	T extractMax();
	
	void IncreaseKey(int i, T key);
}
