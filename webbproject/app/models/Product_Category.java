package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import models.Category;
import models.Product;


@Entity
public class Product_Category {
	
	@Id
	@GeneratedValue
	private int id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	public Product product;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	public Category category;
	
	
	public int getId() {
		return id;
	}
	

}