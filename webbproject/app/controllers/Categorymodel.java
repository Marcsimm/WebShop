package controllers;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import models.Category;
import models.Product;
import models.Product_Category;
import models.Staff;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Content;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.form1;
import views.html.main;
import views.html.category.listAllcategory;
import views.html.category.listAllproduct_category;
import views.html.category.showCategory;
import models.Product_Category;
import views.html.category.*;





public class Categorymodel extends Controller {

	@Transactional
	public static Result createcategory(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();


		String categoryname = form.get("Categoryname")[0];
		int Staffboss = Integer.parseInt(form.get("staffid")[0]);

		Staff staff = JPA.em().find(Staff.class, Staffboss);

		Category category  = new Category();
		category.setCategoryname(categoryname);
		category.staff = staff;
		category.staff.getId();
		category.product_category = new LinkedList<Product_Category>();

		JPA.em().persist(category); 

		return ok(main.render(null, null));

	}

	@Security.Authenticated
	@Transactional
	public static Result newcategoryform(){	
		List<Category> categories = JPA.em().createQuery("SELECT a FROM Category a", Category.class).getResultList();
		List<Staff> staffs = JPA.em().createQuery("SELECT a FROM Staff a", Staff.class).getResultList();


		return ok(form1.render(staffs,categories));
	}


	@Transactional
	public static Result showcategory(int id) {
		Category category = JPA.em().find(Category.class, id);
		return ok(showCategory.render(category));
	}


	@Transactional
	public static Result listallcategory(){	
		List<Category> categories = JPA.em().createQuery("SELECT a FROM Category a", Category.class).getResultList();
		return ok(listAllcategory.render(categories));
	}



	@Transactional
	public static Result listalljsoncategory(){	
		List<Category> categories = JPA.em().createQuery("SELECT a FROM Category a", Category.class).getResultList();
		return ok(Json.toJson(categories));
	}





	@Transactional
	public static Result listAllproduct_category(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		Integer categoryId = Integer.parseInt(form.get("category-id")[0]);

		Category category = JPA.em().find(Category.class, categoryId);

		TypedQuery<Product_Category> query = JPA.em().createQuery( "SELECT a FROM Product_Category a WHERE a.category = :minParam1", Product_Category.class);
		List<Product_Category> Product_category = query.setParameter("minParam1", category).getResultList();

		return  ok(listAllproduct_category.render(Product_category));



	}
	
	@Transactional
	public static Result listAlljsonproduct_category(){
		
	List<Product_Category> query = JPA.em().createQuery( "SELECT a FROM Product_Category a",Product_Category.class).getResultList();

		return  ok(Json.toJson(query));



	}

}      
