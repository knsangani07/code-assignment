package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Please provide a Product Id")
	private Long productId;

	private Long orderId;

	private String description = "";

	@NotNull(message = "Please provide a quantity")
	private int quantity;

	private double itemTotalPrice;

	// avoid this "No default constructor for entity"
	public Item() {
	}

	public Item(Long id, Long productId, Long orderId, String description, int quantity, double itemTotalPrice) {
		this.id = id;
		this.productId = productId;
		this.description = description;
		this.quantity = quantity;
		this.itemTotalPrice = itemTotalPrice;
	}

	public Item(Long productId, Long orderId, int quantity, double itemTotalPrice) {
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.itemTotalPrice = itemTotalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public double getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item{" + "id=" + id + ", productId='" + productId + '\'' + ", quantity='" + quantity + '\''
				+ ", itemTotalPrice=" + itemTotalPrice + '}';
	}
}
