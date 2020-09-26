package model;

public class Queue<T extends Comparable<T>> implements IQueue<T> {

	private Node first;
	private Node last;
	private int size = 0;
	
	public Queue() {
		first = null;
		last = null;
	}


	@Override
	public void enqueue(T value) {
		Node newNode = new Node(value,null,null);
		
		if(first==null) {
			first = newNode;
			last = newNode;
		}else {
			last.setNext(newNode);
			newNode.setPrevious(last);
			last = newNode;
		}
		size++;
	}


	@Override
	public boolean isEmpty() {
		boolean isEmpty = true;
		
		if(first!=null) {
			isEmpty = false;
		}
		
		return isEmpty;
	}


	@Override
	public T front() {
		
		return (T) first.getValue();
	}


	@Override
	public T dequeue() {
		T eliminated = (T) first.getValue();
		
		if(first!=null) {
			if(first==last) {
				first = null;
				last = null;
			}else {
				first = first.getNext();
				first.setPrevious(null);
			}
		}
		if(eliminated!=null) {
			size--;
		}
		return eliminated;
	}


	@Override
	public int size() {
		return size;
	}

}
