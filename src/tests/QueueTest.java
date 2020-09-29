package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import customStructureExceptions.EmptyStructureException;
import structures.Queue;

class QueueTest {

	private Queue<Client> setup1() {
		Queue<Client> clients = new Queue<>();
		
		clients.enqueue(marco());
		clients.enqueue(david());
		
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
	public void enQueueTest() {
		Queue<Client> clients = new Queue<>();
		
		clients.enqueue(marco());
		assertTrue(clients.size() == 1, "enQueue is not adding clients to the queue when the queue is empty.");
		
		clients.enqueue(david());
		assertTrue(clients.size() == 2, "enQueue is not adding clients to the queue when it has one previous client.");
		
		clients.enqueue(laura());
		assertTrue(clients.size() == 3, "enQueue is not adding clients to the queue when it has more than 1 previous client.");
	}
	
	@Test
	public void deQueueTest () {
		Queue<Client> clients = setup1();
		clients.dequeue();
		assertTrue(clients.size() == 1, "deQueue is not removing clients from the queue when it has more than 1 previous client.");
		
		clients.enqueue(laura());
		assertTrue(clients.size() == 2, "deQueue obstructs the addition of new clients after it removes one.");
		
		clients.dequeue();
		assertTrue(clients.size() == 1, "deQueue is not removing clients from the queue when it has more than 1 previous client.");
		
		clients.dequeue();
		assertTrue(clients.size() == 0, "deQueue is not removing clients from the queue when it has 1 previous client.");
		
		assertNull(clients.dequeue(), "deQueue is removing clients in an empty queue."); 
	}	
	
	@Test
	public void isEmptyTest() {
		Queue<Client> clients = setup1();
		assertFalse(clients.isEmpty(),"isEmpty doesn't find clients in an array with more than one client.");
		
		clients.dequeue();
		clients.dequeue();
		assertTrue(clients.isEmpty(),"isEmpty finds clients in an empty queue.");
	
		clients.enqueue(laura());
		assertFalse(clients.isEmpty(),"isEmpty doesn't find clients in an array with one client.");
		
		clients.dequeue();
		assertTrue(clients.isEmpty(),"isEmpty finds clients in an empty queue.");		
	}
	
	@Test
	public void frontTest() {
		Queue<Client> clients = setup1();
		assertTrue(clients.front().getName().equals("Marco Vasquez"), "Front is not returning the client at the front of the queue.");
		
		clients.dequeue();
		assertTrue(clients.front().getName().equals("David Montoya"), "Front is not returning the client at the front of the queue.");
		
		clients.dequeue();
		assertTrue(clients.front()==null, "Front is not returning the client at the front of the queue.");

		clients.enqueue(laura());
		assertTrue(clients.front().getName().equals("Laura Torres"),"Front is not returning the correct client at the front of the queue.");
	}
}
