package model;

public interface IQueue<T extends Comparable<T>> {
	
	public void enqueue(T value);
	
	public boolean isEmpty();
	
	public T front();
	
	public T dequeue();

	public int size();
}
