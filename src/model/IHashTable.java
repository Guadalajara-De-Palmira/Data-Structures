package model;

public interface IHashTable<K,E> {
	
	public void tableInsert(E element);
	
	public E tableRetrieve(K key);
	
	public E tableDelete(K key);
	
	public boolean isEmpty();
	
	public int tableLength();
	
	public int hashFunction(int key);
	
}
