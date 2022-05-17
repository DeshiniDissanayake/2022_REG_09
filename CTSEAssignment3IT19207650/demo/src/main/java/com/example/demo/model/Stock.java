package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	private long id;
	private String Name;
	private int Amount;
	
	public Stock() {
		
	}
	
	public Stock(String Name, int Amount) {
		this.Name = Name;
		this.Amount = Amount;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	
	
	@Column(name = "amount", nullable = false)
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int Amount) {
		this.Amount = Amount;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", Name=" + Name + ", Amount=" + Amount
				+ "]";
	}

}
