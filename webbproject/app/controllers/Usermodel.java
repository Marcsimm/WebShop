package controllers;

import java.util.List;
import java.util.Map;

import models.Category;
import models.Product;
import models.Product_Category;
import models.Shoppingbasket;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;
import views.html.user.*;



public class Usermodel extends Controller{
	
	@Transactional
	public static Result createuser(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		
	
		String email = form.get("Email")[0];    
		String firstname = form.get("FirstName")[0];
		String surname = form.get("SurName")[0];
		String password = form.get("Password")[0];
		String postcode = form.get("Postcode")[0];
		String Town = form.get("Town")[0];
		String adress = form.get("Adress")[0];
		String phonenumber = form.get("PhoneNumber")[0];
		
		User user = new User();	
		
		user.setEmail(email);
		user.setFirstName(firstname);
		user.setSurName(surname);
		user.setPassword(password);
		user.setPostCode(postcode);
		user.setTown(Town);
		user.setStreetAddress(adress);
		user.setTelephone(phonenumber);
		
		
		JPA.em().persist(user);
		
		return ok(main.render(null, null));
		
	}
	

	@Transactional
	
	public static Result newuserform(){	
		 
		List<User> users = JPA.em().createQuery("SELECT a FROM User a", User.class).getResultList();
		
			return ok(form3.render(users));
		}

	@Transactional
	public static Result listAlluser(){
		
	List<User> users = JPA.em().createQuery("SELECT a FROM User a", User.class).getResultList();
	
		return ok(listAlluser.render(users));
		}
	}
	
	
