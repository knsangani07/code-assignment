package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderDetail {

	@Id
	@GeneratedValue
	private Long id;

	private String customerInfo = "";

	private String description = "";

	@NotNull(message = "Please provide a Sub Total")
	private double subTotal;

	@NotNull(message = "Please provide a Total")
	private double total;

	@NotNull(message = "Please provide a Tax")
	private double tax;

	private String status;

	@Transient
	private List<Item> itemList = new ArrayList<Item>();

	// avoid this "No default constructor for entity"
	public OrderDetail() {
	}

	public OrderDetail(double subTotal, double total, double tax, double quantity, String status) {
		// this.itemList = itemList;
		this.subTotal = subTotal;
		this.total = total;
		this.tax = tax;
		this.status = status;
	}

	public OrderDetail(Long id, String customerInfo, String description, double subTotal, double total, double tax,
			double quantity) {
		this.id = id;
		this.customerInfo = customerInfo;
		this.description = description;
		this.subTotal = subTotal;
		this.total = total;
		this.tax = tax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "OrderDetail{" + "id=" + id + ", customerInfo='" + customerInfo + '\'' + ", tax='" + tax + '\''
				+ ", subTotal='" + subTotal + '\'' + ", total='" + total + '\'' + ", customerInfo='" + customerInfo
				+ '\'' + ", status=" + status + '}';
	}
}
