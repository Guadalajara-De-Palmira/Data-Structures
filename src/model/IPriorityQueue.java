package model;

import java.util.List;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.KeyDifferenceException;

public interface IPriorityQueue<T extends Comparable<T>> {
	
	void insert(T newElement);
	
	T maximum();
	
	T extractMax() throws EmptyStructureException;
	
	void IncreaseKey(int i, T key) throws KeyDifferenceException;
	
	public List<T> getList();
}
