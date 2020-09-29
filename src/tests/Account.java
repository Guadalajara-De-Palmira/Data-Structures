package tests;

public class Account {
	
	private int id;
	private double savings;
	private boolean active;
	
	public Account(int id, double savings) {
		this.id = id;
		this.savings = savings;
		this.active = true;
	}

	//getters
	public int getId() {
		return id;
	}

	public double getSavings() {
		return savings;
	}

	public boolean isActive() {
		return active;
	}
	
	//setters
	public void setSavings(double savings) {
		this.savings = savings;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}