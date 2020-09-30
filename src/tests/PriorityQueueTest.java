package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.KeyDifferenceException;
import structures.PriorityQueue;
import structures.Queue;

class PriorityQueueTest {
	
	private PriorityQueue<Client> setup1() throws KeyDifferenceException {
		PriorityQueue<Client> clients = new PriorityQueue<>(10);
		
		clients.insert(walter());
		clients.insert(hector());
		clients.insert(skyler());
		
		return clients;
	}
	
	public Client walter() {
		Account account1 = new Account (634188, 80000000);
		CreditCard cCard1 = new CreditCard(000777, 500000);
		Calendar date1 = new GregorianCalendar(2004, 7, 24);
		Client client1 = new Client("Walter White", 8005439, account1, cCard1, date1, 1);
		return client1;
	}
	
	public Client hector() {
		Account account2 = new Account (412644, 100000000);
		Calendar date2 = new GregorianCalendar(2001, 11, 1);
		Client client2 = new Client("Hector Salamanca", 4651235, account2, null, date2, 3);
		return client2;
	}
	
	public Client skyler() {
		Account account3 = new Account (368512, 40000000);
		CreditCard cCard3 = new CreditCard(546398, 250000);
		Calendar date3 = new GregorianCalendar(2006, 12, 11);
		Client client3 = new Client("Skyler White", 7654251, account3, cCard3, date3, 2);
		return client3;
	}
	
	@Test
	public void insertTest () throws KeyDifferenceException {
		PriorityQueue<Client> clients = new PriorityQueue<>(10);
		clients.insert(walter());
		List<Client> clientsList = clients.getList();
		assertTrue(clientsList.size()==1, "Insert is not adding a client in an empty priority queue.");
		
		clients.insert(hector());
		clientsList = clients.getList();
		assertTrue(clientsList.size()==2, "Insert is not adding a client in a priority queue with one client.");
	
		clients.insert(skyler());	
		clientsList = clients.getList();
		assertTrue(clientsList.size()==3, "Insert is not adding a client in a priority queue with more than one client.");
	}
	
	@Test
	public void extractMaxTest() throws KeyDifferenceException, EmptyStructureException {
		PriorityQueue<Client> clients = setup1();
		Client extracted = clients.extractMax();
		assertTrue(extracted.getName().equals("Hector Salamanca"),"ExtractMax is not removing the front client in a priority queue with more than one client.");
		
		clients.insert(hector());
		List<Client> clientsList = clients.getList();
		assertTrue(clientsList.size()==3, "ExtractMax blocks the addition of new clients after an extraction.");
		
		extracted = clients.extractMax();
		assertTrue(extracted.getName().equals("Hector Salamanca"),"ExtractMax is not removing the front client after adding a client to a priority queue.");
		
		extracted = clients.extractMax();
		assertTrue(extracted.getName().equals("Skyler White"),"ExtractMax is not removing the front client in a priority queue with more than one client.");
		
		extracted = clients.extractMax();
		assertTrue(extracted.getName().equals("Walter White"),"ExtractMax is not removing the front client in a priority queue with one client.");
		
		try {
		extracted = clients.extractMax();
		fail("ExtractMax is extracting a client in an empty priority queue.");
		} catch(EmptyStructureException ese) {
		}
	}
	
	@Test
	public void maximumTest() throws KeyDifferenceException, EmptyStructureException {
		PriorityQueue<Client> clients = setup1();
		Client max = clients.maximum();
		List<Client> clientsList = clients.getList();
		assertTrue(max.getName().equals("Hector Salamanca") && clientsList.size() == 3, "Maximum is not returning the max value a the priority list with more than one client.");
		
		clients.extractMax();
		max = clients.maximum();
		clientsList = clients.getList();
		assertTrue(max.getName().equals("Skyler White") && clientsList.size() == 2, "Maximum is not returning the max value a the priority list with more than one client.");
		
		clients.extractMax();
		max = clients.maximum();
		clientsList = clients.getList();
		assertTrue(max.getName().equals("Walter White") && clientsList.size() == 1, "Maximum is not returning the max value of a priority list with one client.");
		
		clients.extractMax();
		max = clients.maximum();
		assertNull(max, "Maximum is returning a client from an empty priority queue.");
	}
}
