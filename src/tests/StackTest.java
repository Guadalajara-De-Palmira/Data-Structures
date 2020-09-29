package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import structures.Stack;

class StackTest {
	
	public Stack<Client> setup1() {
		Stack<Client> undo = new Stack<>();
		
		undo.push(marco());
		undo.push(david());
		
		return undo;
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
	public void pushTest() {
		Stack<Client> undo = new Stack<>();
		undo.push(marco());
		assertTrue(undo.size() == 1, "Push doesn't add a client to an empty stack.");

		undo.push(david());
		assertTrue(undo.size() == 2, "Push doesn't add a client to an empty stack.");

		undo.push(laura());
		assertTrue(undo.size() == 3, "Push doesn't add a client to an empty stack.");
	}
	
	@Test
	public void popTest() {
		Stack<Client> undo = setup1();
		undo.pop();
		assertTrue(undo.size() == 1, "Pop isn't removing clients from a stack with more than one client.");

		undo.push(david());
		assertTrue(undo.size() == 2, "Pop is not letting more clients get pushed onto the stack.");
		
		undo.pop();
		assertTrue(undo.size() == 1, "Pop isn't removing clients from a stack with more than one client.");
		
		undo.pop();
		assertTrue(undo.size() == 0, "Pop isn't removing clients from a stack with one client.");
		
		undo.pop();
		assertNull(undo.pop(),"Pop is removing a client from an empty stack.");
	}
	
	@Test
	public void topTest() {
		Stack<Client> undo = setup1();
		assertTrue(undo.top().getName().equals("David Montoya"),"Top doesn't return the client in top of a stack with more than one client.");
		
		undo.push(laura());
		assertTrue(undo.top().getName().equals("Laura Torres"),"Top doesn't return the client in top of the stack after a push.");
		
		undo.pop();
		assertTrue(undo.top().getName().equals("David Montoya"),"Top doesn't return the client in top of the stack after a pop.");
		
		undo.pop();
		assertTrue(undo.top().getName().equals("Marco Vasquez"),"Top doesn't return the client in top of a stack with just one client.");
		
		undo.pop();
		assertNull(undo.top(),"Top is returning a client from an empty stack.");
	}
}
