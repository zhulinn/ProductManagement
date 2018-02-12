package me.zhulin.ProductManagement.beans;

import java.io.Serializable;

public class Product implements Serializable{
	private String code;
	private String name;
	private float price;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	public Product(String code, String name, float price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

}
