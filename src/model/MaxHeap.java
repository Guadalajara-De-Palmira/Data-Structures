package model;

public class MaxHeap <T extends Comparable<T>> implements IMaxHeap<T>{

	private Component[] elements;
	int heapSize;
	
	public MaxHeap(int maxSize) {
		elements = new Component[maxSize];
		heapSize = 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void heapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest = i;
		int comparation = elements[l].getElement().compareTo(elements[i].getElement());
		
		if(l<heapSize && comparation>0) {
			largest = l;
			
		}
		comparation = elements[r].getElement().compareTo(elements[largest].getElement());
		
		if(l<heapSize && comparation>0) {
			largest = r;
		}
		if(largest != i) {
			Component aux = elements[i];
			elements[i] = elements[largest];
			elements[largest] = aux;
			aux = null;
			heapify(largest);
		}
		
	}

	@Override
	public void buildMaxHeap() {
		for(int i=elements.length;i>=0;i--) {
			heapify(i);
		}
		
	}

	@Override
	public void insert(T element) {
		heapSize++;
		increaseKey(heapSize, element);
		
	}

	@Override
	public T extract() {
		if(heapSize<1) {
			//tirar una excepcion
		}
		Component max = elements[1];
		elements[1] = elements[heapSize];
		elements[heapSize] = null;
		heapSize--;
		heapify(1);
		
		return (T)max.getElement();
	}

	@Override
	public T returnMaximum() {
		//buildMaxHeap();
		return (T)elements[1].getElement();
	}

	@Override
	public int parent(int i) {
		return i/2;
	}

	@Override
	public int left(int i) {
		return 2*i;
	}

	@Override
	public int right(int i) {
		// TODO Auto-generated method stub
		return (2*i)+1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void increaseKey(int i, T key) {
		if(key.compareTo((T)elements[i].getElement())<0) {
			//error, la nueva llave es menor que la actual
		}
		
		elements[i] = new Component(key);
		int comparation = elements[parent(i)].getElement().compareTo(elements[i].getElement());
		
		while( i>i && comparation<0) {
			Component aux = elements[parent(i)];
			elements[parent(i)] = elements[i];
			elements[i] = aux;
			i = parent(i);
			comparation = elements[parent(i)].getElement().compareTo(elements[i].getElement());
		}
	}

}
