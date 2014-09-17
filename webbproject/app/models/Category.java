
package models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import models.Staff;

@Entity
public class Category{


	@Id
	@GeneratedValue
	private int id;
	private String categoryname;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="staff_id")
	public Staff staff;
	@JsonIgnore
	@OneToMany(mappedBy= "category")
	public List<Product_Category> product_category;

	public Category() {}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public int getId() {
		return id;
	}



}






