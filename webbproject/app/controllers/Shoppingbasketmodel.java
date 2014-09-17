package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import controllers.MyAuthentication;

import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import models.Orders;
import models.Product;
import models.Category;
import models.Product_Category;
import models.Staff;
import models.User;
import models.Shoppingbasket;
import views.html.*;
import views.html.helper.form;
import views.html.product.listAllproduct;

import java.util.Set;

public class Shoppingbasketmodel extends Controller {



	@Transactional
	@Security.Authenticated
	public static Result listAlshoopingbasket(){

		User user = MyAuthentication.getActivUser().get(0);
		return ok(listAllshoopingbasket.render(user.shoppingbasket));

	}
	@Transactional
	public static Result listAlljsonshoopingbasket(){

		User user = MyAuthentication.getActivUser().get(0);
		return ok(Json.toJson(user.shoppingbasket));

	}

	@Transactional
	@Security.Authenticated
	public static Result addtobasket(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();

		User userActive = MyAuthentication.getActivUser().get(0);

		Integer productId = Integer.parseInt(form.get("product-id")[0]);
		Integer proquantity = Integer.parseInt(form.get("quantity")[0]);

		Product product = JPA.em().find(Product.class,  productId);


		Shoppingbasket basket = new Shoppingbasket();

		basket.setUser(userActive);
		basket.setProduct(product);
		basket.setQuantity(proquantity);

		JPA.em().persist(basket);


		return redirect(routes.Productmodel.listallproduct());


	}

	@Transactional
	public static Result ordernow(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();		
		User activeUser = MyAuthentication.getActivUser().get(0);		
		List<Shoppingbasket> cartRecords = activeUser.shoppingbasket;


		if ((form.get("deleteall")) != null){
			String deletec = form.get("deleteall")[0];
			if (deletec.equals("yes")){
				for (Shoppingbasket cartRecord : cartRecords) {
					Orders order = new Orders();
					order.setProductname(cartRecord.getProduct().getProductName());

					order.setQuantity(cartRecord.getQuantity());
					order.setUser(activeUser);

					SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
					String dateString = sdf.format(new Date()); 		
					try {
						Date date = sdf.parse(dateString);
						order.setDate(date);
					} catch (ParseException e) {			
						e.printStackTrace();
					}

					JPA.em().persist(order);
					JPA.em().remove(cartRecord);
				}
			}
		}	
		return  ok(main.render(null, null));	
	}


	@Transactional
	public static Result orderOnMobile(){	
		User activeUser = MyAuthentication.getActivUser().get(0);		
		List<Shoppingbasket> cartRecords = activeUser.shoppingbasket;
		for (Shoppingbasket cartRecord : cartRecords) {
			
			Orders order = new Orders();
			order.setProductname(cartRecord.getProduct().getProductName());
			order.setQuantity(cartRecord.getQuantity());
			order.setUser(activeUser);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String dateString = sdf.format(new Date()); 		
			try {
				Date date = sdf.parse(dateString);
				order.setDate(date);
			} catch (ParseException e) {			
				e.printStackTrace();
			}

			JPA.em().persist(order);
			JPA.em().remove(cartRecord);
		}

		return  ok(main.render(null, null));	
	}

	@Transactional
	@Security.Authenticated
	public static Result listalljsonorders(){

		User user = MyAuthentication.getActivUser().get(0);

		return ok(Json.toJson(user.order));
	}

	//	@Transactional
	//	public static Result listAlljsonshoopingbasket(){
	//
	//		User user = MyAuthentication.getActivUser().get(0);
	//		return ok(Json.toJson(user.shoppingbasket));
	//
	//}







}




