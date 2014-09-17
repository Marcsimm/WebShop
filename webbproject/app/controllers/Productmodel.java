package controllers;




import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import models.Category;
import models.Product;
import models.Product_Category;
import models.Shoppingbasket;
import models.Staff;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import views.html.category.listAllproduct_category;
import views.html.product.listAllproduct;
import play.mvc.Security;
import views.html.product.*;


public class Productmodel extends Controller {

	@Transactional
	public static Result createproduct(){
		
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String name = form.get("Productname")[0];
		String description = form.get("Description")[0];
		double cost = Double.parseDouble(form.get("Cost")[0]);
		double rrp =  Double.parseDouble(form.get("Rrp")[0]);
	
		Product product = new Product();
		product.setProductName(name);
		product.setDescription(description);
		product.setCost(cost);
		product.setRrp(rrp);
		

		JPA.em().persist(product);
		JPA.em().flush();
		
		for(String catId : form.get("categoryid")) {
			Category category = JPA.em().find(Category.class, Integer.parseInt(catId));
			
	   Product_Category product_category  = new Product_Category ();
	   product_category.category = category;
	   product_category.product = product; 
	   
		JPA.em().persist(product_category);
	}
	   
		
		
		return  ok(main.render(null, null));
		
	}

	@Transactional
	@Security.Authenticated
	public static Result newproductform(){	
		List<Shoppingbasket> shoppingsbaskets = JPA.em().createQuery("SELECT a FROM Shoppingbasket a", Shoppingbasket.class).getResultList();
		List<Category> categories = JPA.em().createQuery("SELECT a FROM Category a", Category.class).getResultList();
		List<Product_Category> Product_Categorys = JPA.em().createQuery("SELECT a FROM Product_Category a", Product_Category.class).getResultList();
		List<Product> products = JPA.em().createQuery("SELECT a FROM Product a", Product.class).getResultList();
		
			return ok(form.render(Product_Categorys, categories,products));
		}
	
	@Transactional
	public static Result listallproduct(){	
	List<Product> products = JPA.em().createQuery("SELECT a FROM Product a", Product.class).getResultList();
		return ok(listAllproduct.render(products));
	}
	
	@Transactional
	public static Result listalljsonproduct(){	
	List<Product> products = JPA.em().createQuery("SELECT a FROM Product a", Product.class).getResultList();
		return ok(Json.toJson(products));
	}
	
	
	
	
	@Transactional
	public static Result showProduct(int id) {
	Product product = JPA.em().find(Product.class, id);
	return ok(showProduct.render(product));
	}
	

	
    
    

	
	
	
}





