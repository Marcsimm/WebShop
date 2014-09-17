package controllers;



import java.util.List;

import models.Product;
import models.Staff;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

public class startpagemodel extends Controller {

		@Transactional	
		public static Result startpage(){
			
			return ok(main.render(null, null));
		}
		
		@Transactional
		public static Result listalljsontsaff(){	
		List<Staff> staffs = JPA.em().createQuery("SELECT a FROM Staff a", Staff.class).getResultList();
			return ok(Json.toJson(staffs));
		}
		
	
}
