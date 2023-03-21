package com.event.java.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.java.model.EventService;
import com.event.java.model.MongoFactory;
import com.event.java.model.UserE;
import com.event.java.model.UserEService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.event.java.controller.user.mservice.enums.RequestType;
import com.event.java.controller.user.mservice.models.PaymentResponse;
import com.event.java.controller.user.mservice.processor.CreateOrderMoMo;
import com.event.java.controller.user.mservice.shared.utils.LogUtils;
import com.event.java.controller.user.mservice.Utils;
import com.event.java.controller.user.mservice.config.Environment;

@Controller
@RequestMapping("/user")
public class UserController {

	static String db_name = "EventManagement", db_collection = "User";
	private static Logger log = Logger.getLogger(UserController.class);

	@Resource(name = "userEService")
	private UserEService userEService;

	// Displaying the initial users list.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPersons(Model model) {
		log.debug("Request to fetch all users from the mongo database");
		List user_list = userEService.getAll();
		model.addAttribute("users", user_list);
		return "welcome";
	}

	// Opening the add new user form page.
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		log.debug("Request to open the new user form page");
		model.addAttribute("userAttr", new UserE());
		return "form";
	}

///--------------------------------------------------------------------------
	// Opening the edit user form page.
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value = "id", required = true) String id, Model model) {
		log.debug("Request to open the edit user form page");
		model.addAttribute("userAttr", userEService.findUserId(id));
		return "edit";
	}
//--------------------------------------------------------------------------
	// Deleting the specified user.
	/*
	 * @RequestMapping(value = "/delete", method = RequestMethod.GET) public String
	 * delete(@RequestParam(value = "id", required = true) String id, Model model) {
	 * userService.delete(id); return "redirect:/user/list"; }
	 */

	// Adding a new user or updating an existing user.
	/*
	 * @RequestMapping(value = "/save", method = RequestMethod.POST) public String
	 * save(@ModelAttribute("userAttr") User user) {
	 * 
	 * userService.edit(user);
	 * 
	 * return "loginsucces"; }
	 */

	// test login
	@RequestMapping(value = "/loginsucces", method = RequestMethod.GET)
	public String loginsucces() {

		return "loginsucces";
	}
	/// ----------------------------------------------------------------
	DBCollection coll_user = MongoFactory.getCollection("EventManagement", "User");
	@RequestMapping(value = "/updateinfor", method = RequestMethod.GET)
	public String update(@RequestParam("id") String id, Model model, HttpSession session) {
		try {
			 UserE u = new UserE();
		      //  DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		        // Fetching the record object from the mongo database.
		        DBObject where_query = new BasicDBObject();
		        where_query.put("Userid", id);

		        DBObject dbo = coll_user.findOne(where_query);
		        u.setId(dbo.get("Userid").toString());
		        u.setName(dbo.get("Username").toString());
		        u.setPhonenum(dbo.get("Numberphone").toString());
		        u.setBirth(dbo.get("BirthDate").toString());
		        u.setEmail(dbo.get("Email").toString());
		        u.setPassword(dbo.get("Password").toString());
		        u.setSex(dbo.get("sex").toString());	
		        u.setImg(dbo.get("Image").toString());
		        
			model.addAttribute("edituser", u);
		} catch (Exception ex) {
		}

		return "edituser";
	}
	

	@RequestMapping(value = "/updateinfor", method = RequestMethod.POST)
	public String updateuser(Model model, HttpServletRequest request,
	  HttpSession session) 
	{ try {
		String id =request.getParameter("Userid"); 
		String name =request.getParameter("Username"); 
		String email =request.getParameter("Email"); 
		String birth =request.getParameter("BirthDate"); 
		String phonenum = request.getParameter("Numberphone");
		String password = request.getParameter("Password");
		String img = request.getParameter("Image");
		String sex= request.getParameter("sex");
		// System.out.print(flowerid + flowername +flowerstock+flowersdecrip+image); 
		DBObject where_query = new BasicDBObject(); 
		where_query.put("id", id); 
		DBObject dbfindupdate = coll_user.findOne(where_query); 
		BasicDBObject edited = new  BasicDBObject(); 
		edited.put("Userid", id); 
		edited.put("Username",EventService.utf8(name)); 
		edited.put("Email",email);
		edited.put("BirthDate", birth); 
		edited.put("Numberphone", phonenum);
		edited.put("Password", password);
		edited.put("sex", sex);
		edited.put("Image", img);
		
		
		coll_user.update(dbfindupdate, edited);
		
	  } catch (Exception ex) { }
	  
	  return "redirect:/user/updateinfor?id"; }

}