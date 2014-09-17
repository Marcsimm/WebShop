package controllers;

import java.util.List;

import models.Staff;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.listAlluser;

public class Staffmodel extends Controller {
	
	@Transactional
	public static Result createstaff(){
	Staff staff = new Staff();
	staff.setDateOfBirth("1992-20-04");
	staff.setEmail("gotham123@gmail.com");
	staff.setFirstName("viktor");
	staff.setSurName("crane");
	staff.setMobile("0789 89898");
	staff.setTown("borås");
	staff.setPostCode("123 897");
	staff.setSalary(300000);
	staff.setStreetAddress("gotham city");
	
	JPA.em().persist(staff);
	
	return ok("staffs created");
	
	}
}

/*
	@Transactional
	public static Result listAlluser(){
		
	List<Staff> staffs = JPA.em().createQuery("SELECT a FROM Staff a", Staff.class).getResultList();
	
		return ok(listAllstaff.render(staffs));
		}
	}

*/
