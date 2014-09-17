package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Product {

	@Id
	@GeneratedValue
	private int id;
	private String productName;
	private String description;
	private double cost;
	private double rrp;





	@OneToMany(mappedBy= "product")
	public List<Product_Category> product_category;

	@JsonIgnore
	@OneToMany(mappedBy= "product1")
	public List<Shoppingbasket> shoppingbasket;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getRrp() {
		return rrp;
	}

	public void setRrp(double rrp) {
		this.rrp = rrp;
	}

	public int getId() {
		return id;
	}



}