package com.example.entity;

public class Person {
	private String _id;
	private String name;
	private String phone;
	private String salary;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public Person() {
		super();
	}
	public Person(String _id, String name, String phone, String salary) {
		super();
		this._id = _id;
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return _id + "," + name + "," + phone + "," + salary;
	}
}
