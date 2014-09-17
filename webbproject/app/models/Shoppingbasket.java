
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import models.User;
import models.Product;

@Entity
public class Shoppingbasket {
	

	@Id
	@GeneratedValue
	private int id;
	private int quantity;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	public Product product1;

	public int getId() {
		return id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setUser(User user) {
		this.user = user;
		
	}
	
	public User getUser() {
		return user;
	}


	public void setProduct(Product product1) {
		this.product1 = product1;
		
	}
	
	public Product getProduct() {
		return product1;
	}
	 
	
}

