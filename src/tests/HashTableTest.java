package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import customStructureExceptions.FullStructureException;
import customStructureExceptions.NotFoundException;
import structures.HashTable;
import structures.Queue;

class HashTableTest {

	
	private HashTable<Integer,Client> setup1() throws FullStructureException {
		HashTable<Integer,Client> clients = new HashTable<>(10);
		
		clients.tableInsert(marco());
		clients.tableInsert(david());
		return clients;
	}
	
	public Client marco() {
		Account account1 = new Account (111222, 3000000);
		CreditCard cCard1 = new CreditCard(333444, 100000);
		Calendar date1 = new GregorianCalendar(2010, 3, 1);
		Client client1 = new Client("Marco Vasquez", 1006309297, account1, cCard1, date1, 0);
		return client1;
	}
	
	public Client david() {
		Account account2 = new Account (100200, 2000000);
		CreditCard cCard2 = new CreditCard(200300, 300000);
		Calendar date2 = new GregorianCalendar(2008, 11, 1);
		Client client2 = new Client("David Montoya", 1005384998, account2, cCard2, date2, 0);
		return client2;
	}
	
	public Client laura() {
		Account account3 = new Account (345123, 200);
		CreditCard cCard3 = new CreditCard(776677, 500000);
		Calendar date3 = new GregorianCalendar(2008, 6, 22);
		Client client3 = new Client("Laura Torres", 1011487566, account3, cCard3, date3, 0);
		return client3;
	}
	
	@Test
	public void tableInsertTest() throws FullStructureException {
		HashTable<Integer,Client> clients = new HashTable<>(3);
		
		clients.tableInsert(marco());
		assertTrue(clients.tableLength() == 1, "tableInsert is not adding clients to the hashTable when the queue is empty.");
		
		clients.tableInsert(david());
		assertTrue(clients.tableLength() == 2, "tableInsert is not adding clients to the hashTable when it has one previous client.");
		
		clients.tableInsert(laura());
		assertTrue(clients.tableLength() == 3, "tableInsert is not adding clients to the hashTable when it has more than 1 previous client.");

		try {
			
			clients.tableInsert(david());
			fail("the tableInsert method is nos throwing exception when it is full");
		}catch(FullStructureException e) {
			
		}
	}
	
	@Test
	public void tableInsertTest2() throws FullStructureException {
		HashTable<Integer,Client> clients = new HashTable<>(5);
		
		clients.tableInsert(marco());
		assertTrue(clients.tableLength() == 1, "tableInsert is not adding clients to the hashTable when the queue is empty.");
		assertTrue(marco().getId() == clients.getTable()[2].getElement().getId());
		
		clients.tableInsert(marco());
		assertTrue(clients.tableLength() == 2, "tableInsert is not adding clients to the hashTable when the queue is empty.");
		assertTrue(marco().getId() == clients.getTable()[3].getElement().getId());
		
		clients.tableInsert(laura());
		assertTrue(clients.tableLength() == 3, "tableInsert is not adding clients to the hashTable when it has more than 1 previous client.");
		assertTrue(laura().getId() == clients.getTable()[1].getElement().getId());
		
		clients.tableInsert(david());
		assertTrue(clients.tableLength() == 4, "tableInsert is not adding clients to the hashTable when it has one previous client.");
		assertTrue(david().getId() == clients.getTable()[4].getElement().getId());
	}

	
	@Test
	public void tableRetrieveTest() throws NotFoundException, FullStructureException {
		HashTable<Integer,Client> clients = new HashTable<>(5);
		
		try {
			clients.tableRetrieve(marco().getId());
			fail("tableRetrieve is finding and element when is shouldn't, because the table is empty");
		}catch(NotFoundException e) {
			
		}
		int key;
		clients.tableInsert(laura());
		key = laura().getId();
		assertTrue(clients.tableRetrieve(key).getId()==laura().getId(),"the hash table is not finding the element when it should");
		
		clients.tableInsert(marco());
		key = marco().getId();
		assertTrue(clients.tableRetrieve(key).getId()==marco().getId(),"the hash table is not finding the element when it should");
		
		clients.tableInsert(marco());
		key = marco().getId();
		assertTrue(clients.tableRetrieve(key).getId()==marco().getId(),"the hash table is not finding the element when it should");
		
		clients.tableInsert(david());
		key = david().getId();
		assertTrue(clients.tableRetrieve(key).getId()==david().getId(),"the hash table is not finding the element when it should");

		
		try {
			clients.tableRetrieve(1005384993);
			fail("tableRetrieve is finding and element when is shouldn't, because the element does not exist in the table");
		}catch(NotFoundException e) {
			
		}
	}
	
	@Test
	public void tableDeleteTest() throws FullStructureException, NotFoundException {
		HashTable<Integer,Client> clients = setup1();
		
		try {
			clients.tableDelete(1003485924);
			fail("the method is not throwing the exception when is should");
		} catch (NotFoundException e) {

		}

		clients.tableDelete(marco().getId());
		
		try {
			clients.tableRetrieve(marco().getId());
			fail("the methos is finding an element that should have been eliminated");
		}catch(NotFoundException e) {
			
		}

		clients.tableDelete(david().getId());
		
		try {
			clients.tableRetrieve(david().getId());
			fail("the methos is finding an element that should have been eliminated");
		}catch(NotFoundException e) {
			
		}
		
		try {
			clients.tableDelete(david().getId());
			fail("the method is not throwing the exception when is should");
		} catch (NotFoundException e) {

		}
		
	}
	
	@Test
	public void tableInsertTest3() throws FullStructureException, NotFoundException {
		HashTable<Integer,Client> clients = setup1();
		
		clients.tableDelete(marco().getId());
		
		try {
			clients.tableInsert(marco());
		}catch(FullStructureException e) {
			fail("the method does not recognize thath the element in thath position has been eliminated and now is an element called deleted which should be overrided with the element marco");
		}

		assertTrue(marco().getId() == clients.getTable()[7].getElement().getId(),"the method add is not overriding the element deleted and is just passing throught it");
	}
	
	@Test
	public void hashFunctionTest() {
		HashTable<Integer,Client> clients = new HashTable<>(5);
		
		assertTrue((marco().hashCode()%5) == clients.hashFunction(marco().hashCode()),"hashFunction method is not working correctly");
		
		assertTrue((laura().hashCode()%5) == clients.hashFunction(laura().hashCode()),"hashFunction method is not working correctly");
		
		assertTrue((david().hashCode()%5) == clients.hashFunction(david().hashCode()),"hashFunction method is not working correctly");
	}
}
