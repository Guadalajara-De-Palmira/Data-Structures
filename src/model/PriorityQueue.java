package model;

import java.util.List;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T>{	
	
	private MaxHeap elements;
	
	public PriorityQueue (int maxSize) {
		elements = new MaxHeap(maxSize);
		
	}

	@Override
	public void insert(T newElement) {
		elements.insert(newElement);
	}

	@Override
	public T maximum() {
		return (T) elements.returnMaximum();
	}

	@Override
	public T extractMax() {
		return (T) elements.extract();
	}

	@Override
	public void IncreaseKey(int i, T key) {
		elements.increaseKey(i, key);
	}

	@Override
	public List<T> getList() {
		return elements.getList();
	}
}
