package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Offers {

	@Id
	@GeneratedValue
	private Long id;

	private Long productId;

	private String productName;

	@NotEmpty(message = "Please provide a Type")
	private String type;

	private int buyQuantity;

	private int freeQuantity;

	private double value;

	private Date startDate;

	private Date endDate;

	private boolean isActive;

	private String description;

	// avoid this "No default constructor for entity"
	public Offers() {
	}

	public Offers(Long productId, String type, int buyQuantity, int freeQuantity, Date startDate, Date endDate,
			boolean isActive) {
		this.productId = productId;
		this.type = type;
		this.buyQuantity = buyQuantity;
		this.freeQuantity = freeQuantity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
	}

	public Offers(Long productId, String type, double value, Date startDate, Date endDate, boolean isActive) {
		this.productId = productId;
		this.type = type;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
	}

	public Offers(Long productId, String productName, String type, double value, Date startDate, Date endDate,
			boolean isActive) {
		this.productId = productId;
		this.productName = productName;
		this.type = type;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public int getFreeQuantity() {
		return freeQuantity;
	}

	public void setFreeQuantity(int freeQuantity) {
		this.freeQuantity = freeQuantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Offers{" + "id=" + id + ", type='" + type + '\'' + ", value='" + value + '\'' + ", isActive=" + isActive
				+ '}';
	}
}
