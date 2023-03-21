package com.event.java.controller.user;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.java.model.MongoFactory;
import com.event.java.model.UserE;
import com.event.java.model.UserEService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Controller
@RequestMapping("/user")
public class LoginController {

	static String db_name = "EventManagement", db_collection = "User";
	private static Logger log = Logger.getLogger(UserController.class);
	DBCollection coll_user = MongoFactory.getCollection(db_name, db_collection);
	@Resource(name = "userEService")
	private UserEService userEService;

	@RequestMapping(value = "checklogingoogle", method = RequestMethod.GET)
	public String checklogingoogle(HttpSession session) {
		UserE u = new UserE();
		UserE googleu = (UserE) session.getAttribute("loginsession");
		try {

			DBObject where_query = new BasicDBObject();
			where_query.put("Email", googleu.getEmail());

			DBObject dbo = coll_user.findOne(where_query);
			u.setId(dbo.get("Userid").toString());
			u.setName(dbo.get("Username").toString());
			u.setEmail(dbo.get("Email").toString());
			u.setType(dbo.get("Types").toString());
			u.setSex(dbo.get("sex").toString());
			u.setBirth(dbo.get("Birth").toString());
			u.setPhonenum(dbo.get("Numberphone").toString());
			u.setImg(dbo.get("Image").toString());
			session.setAttribute("loginsession", u);

			if (u.getType().contains("customer")) {

				return "redirect:/event/list";
			} else {

				return "redirect:/admin/welcome";
			}

		} catch (Exception e) {

			String id = "";
			DBCursor cursor = coll_user.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();
				id = dbObject.get("Userid").toString();
			}
			// Create a new object and add the new user details to this object.
			Integer temp = Integer.parseInt(id) + 1;
			BasicDBObject doc = new BasicDBObject();
			doc.put("Userid", Integer.toString(temp));
			doc.put("Image", "");
			doc.put("sex", "");
			doc.put("Username", googleu.getEmail());
			//
			String md5Hex = DigestUtils.md5Hex("$!#@^#$%^#$%#$%DoBaN^&%Bi@tDuoc@P^S$D0").toUpperCase();
			doc.put("" + "Password", md5Hex);
			doc.put("Types", "customer");
			doc.put("Email", googleu.getEmail());
			doc.put("Birth", "Cap nhat");
			doc.put("Numberphone", "000000000");
			
			UserE tempus = new UserE();
			tempus.setEmail(googleu.getEmail());
			tempus.setId(Integer.toString(temp));
			tempus.setName(googleu.getEmail());
			tempus.setType("customer");
			tempus.setSex("No");
			tempus.setImg("");
			tempus.setBirth("Cap nhat");
			tempus.setPhonenum("000000000");
			// Save a new user to the mongo collection.
			coll_user.insert(doc);
			session.setAttribute("loginsession", tempus);
			return "redirect:/event/list";

		}

	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		try {
			UserE checklogin = (UserE) session.getAttribute("loginsession");
			if (!checklogin.getName().isEmpty()) {
				if (checklogin.getType().contains("customer")) {

					return "redirect:/event/list";
				} else {

					return "redirect:/admin/welcome";
				}
			} else
				return "login";

		} catch (Exception ex) {
			return "login";
		}

	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.setAttribute("loginsession", null);
		return "login";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, ModelMap modelMap) {
		UserE u = new UserE();
		String md5Hex = DigestUtils.md5Hex(password).toUpperCase();
		try {

			DBObject where_query = new BasicDBObject();
			where_query.put("Password", md5Hex);
			where_query.put("Email", username.toString());

			DBObject dbo = coll_user.findOne(where_query);
			u.setId(dbo.get("Userid").toString());
			u.setName(dbo.get("Username").toString());
			u.setEmail(dbo.get("Email").toString());
			u.setType(dbo.get("Types").toString());
			u.setPassword(dbo.get("Password").toString());
			u.setBirth(dbo.get("BirthDate").toString());
			u.setImg(dbo.get("Image").toString());
			u.setSex(dbo.get("sex").toString());
			u.setPhonenum(dbo.get("Numberphone").toString());

		} catch (Exception e) {
			/* modelMap.put("toastshow", "Đăng nhập không thành công!"); */
			modelMap.put("error", "Không tồn tại Email này trong hệ thống hoặc Sai tài khoản hoặc mật khẩu !");
			return "login";
		}
		if (username.equals(u.getEmail()) && md5Hex.equals(u.getPassword())) {
			session.setAttribute("loginsession", u);
			if (u.getType().contains("customer")) {

				return "redirect:/event/list";
			} else {

				return "redirect:/admin/welcome";
			}
		} else {
			modelMap.put("error", "Sai tài khoản hoặc mật khẩu !");

			return "login";
		}
	}

	@RequestMapping(value = "regis", method = RequestMethod.GET)
	public String regis(Model model) {
	    model.addAttribute("userForm", new UserE());
	    return "Register";
	}

	@RequestMapping(value = "regis", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") UserE userForm, BindingResult result, ModelMap modelMap) {
	    String username = userForm.getName();
	    String password = userForm.getPassword();
	    String email = userForm.getEmail();
	    String sex = userForm.getSex();
	    String image = userForm.getImg();
	    String birth = userForm.getBirth();
	    String phonenum = userForm.getPhonenum();

	    if (result.hasErrors()) {
	        modelMap.put("error", "Vui lòng nhập đầy đủ thông tin!");
	        return "Register";
	    }

	    try {
	        DBObject where_query = new BasicDBObject();
	        where_query.put("Email", email.toString());
	        DBObject dbo = coll_user.findOne(where_query);
	        if (dbo != null) {
	            modelMap.put("error", "Đã tồn tại email hoặc tài khoản này trong hệ thống!");
	            return "Register";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    String md5Hex = DigestUtils.md5Hex(password).toUpperCase();
	    String id = "";
	    DBCursor cursor = coll_user.find();
	    while (cursor.hasNext()) {
	        DBObject dbObject = cursor.next();
	        id = dbObject.get("Userid").toString();
	    }

	    Integer temp = Integer.parseInt(id) + 1;
	    BasicDBObject doc = new BasicDBObject();
	    doc.put("Userid", Integer.toString(temp));
	    doc.put("UserName", username.toString());
	    doc.put("Password", md5Hex);
	    doc.put("Types", "customer");
	    doc.put("Email", email.toString());
	    doc.put("Image", "https://tse3.mm.bing.net/th?id=OIP.3IsXMskZyheEWqtE3Dr7JwHaGe&pid=Api&P=0");
	    doc.put("sex", sex.toString());
	    doc.put("BirthDate", birth.toString());
	    doc.put("Numberphone", phonenum.toString());
	    coll_user.insert(doc);
	    
	    return "login";
	}

	@RequestMapping(value = "forgot", method = RequestMethod.GET)
	public String forgot() {

		return "forgotpassword";
	}

	@RequestMapping(value = "forgot", method = RequestMethod.POST)
	public String forgot(@RequestParam("email") String email, HttpSession session, ModelMap modelMap) {
		UserE u = new UserE();
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			DBObject where_query = new BasicDBObject();
			where_query.put("email", email.toString());

			DBObject dbo = coll.findOne(where_query);
			u.setId(dbo.get("Userid").toString());
			u.setName(dbo.get("Username").toString());
			u.setPassword(dbo.get("Password").toString());
			u.setType(dbo.get("Types").toString());
			u.setEmail(dbo.get("Email").toString());
			u.setImg(dbo.get("Image").toString());
			u.setBirth(dbo.get("Birth").toString());
			u.setSex(dbo.get("sex").toString());
			u.setPhonenum(dbo.get("Numberphone").toString());

			if (email.equals(u.getEmail())) {

				// xu ly email
				String pass = generatePassword(12);
				// save
				String md5Hex = DigestUtils.md5Hex(pass).toUpperCase();
				BasicDBObject edited = new BasicDBObject();
				edited.put("Userid", u.getId());
				edited.put("Username", u.getName());
				edited.put("Password", md5Hex);
				edited.put("Types", u.getType());
				edited.put("sex", u.getSex());
				edited.put("Image", u.getImg());
				edited.put("Email", u.getEmail());
				edited.put("BirthDate", u.getBirth());
				edited.put("Numberphone", u.getPhonenum());

				coll.update(dbo, edited);
				//
				AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

				MailService mailService = context.getBean("mailService", MailServiceImpl.class);

				String senderEmailId = "tiennguyennaraka@gmail.com";
				String receiverEmailId = email;
				String subject = "[Quên Mật Khẩu] Thư gửi mật khẩu mới của bạn.";
				String message = "<div id=':33' class='ii gt' jslog='20277; u014N:xr6bB; 4:W251bGwsbnVsbCxbXV0.'>"
						+ "<div id=':32' class='a3s aiL '>" + "<u>" + "</u>" + "<div>"
						+ "<div style='width:800px;text-align:center;margin:0 auto'>"
						+ "<table align='center' width='800' height='1000' cellpadding='0' cellspacing='0' border='0' style='background:#A77979'>"
						+ "<tbody>" + "<tr>"
						+ "<td align='center' valign='top' style='background:url(https://wallpapercave.com/uwp/uwp2052576.jpeg)'>"
						+ "<table width='576' cellpadding='0' cellspacing='0' border='0'>" + "<tbody>" + "<tr>"
						+ "<td height='250'>" + "</td>" + "</tr>" + "<tr>"
						+ "<td align='left' valign='top' style='color:#fff'>"
						+ "<font color='#e35b5b' style='font-size:26px'>" + "<strong>" + "Gửi khách hàng:" + "<br>"
						+ "Đây đây là mật khẩu của bạn, vui lòng nhập vào:" + "</strong>" + "</font>" + "</td>"
						+ "</tr>" + "<tr>" + "<td height='40' valign='top'>" + "</td>" + "</tr>" + "<tr>"
						+ "<td align='center' height='54' style='background:#202121;letter-spacing:15px;color:#ffffff'>"
						+ "<font size='6' color='#FFFFFF'>" +
						// code here
						pass +

						"</font>" + "</td>" + "</tr>" + "<tr>" + "<td height='40' valign='top'>" + "</td>" + "</tr>"
						+ "<tr>" + "<td valign='top' style='color:#e35b5b'>" + "<font size='4' color='#e35b5b'>"
						+ "Mọi thắc mắc vui lòng gửi email "
						+ "<a href='mailto:longan04111@gmail.com ' target='_blank'>" + "longan04111@gmail.com" + "</a>"
						+ "để biết thêm thông tin." +

						"Nếu bạn không có thắc mắc nào, vui lòng đừng gửi thư rác!." + "</font>" + "</td>" + "</tr>"
						+ "<tr>" + "<td height='40' valign='top'>" + "</td>" + "</tr>" + "<tr>"
						+ "<td height='60' valign='top' style='color:#e35b5b'>" + "<font size='4' color='#e35b5b'>"
						+ "Một ngày tốt lành！" + "</font>" + "</td>" + "</tr>" + "<tr>"
						+ "<td height='50' valign='top' style='color:#e35b5b'>" + "<font size='5' color='#e35b5b'>"
						+ "<strong>" + "TN" + "</strong>" + "</font>" + "</td>" + "</tr>" + "<tr>"
						+ "<td height='100%'>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>"
						+ "</tbody>" + "</table>" + "</div>" + "</div>" + "</div>" + "<div class='yj6qo'>" + "</div>"
						+ "</div>";

				mailService.sendEmail(senderEmailId, receiverEmailId, subject, message);
				context.close();
				return "login";
			} else {
				modelMap.put("error", "Không tìm thấy Email trong hệ thống!");

				return "forgotpassword";
			}
		} catch (Exception e) {
			/* modelMap.put("toastshow", "Đăng nhập không thành công!"); */
			modelMap.put("error", "Không tìm thấy Email trong hệ thống!");
			return "forgotpassword";
		}

	}

	private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBERS = "0123456789";
	private static final String SYMBOLS = "!@#$%^&*_=+-/";

	private static final String PASSWORD_CHARS = LOWER_CASE + UPPER_CASE + NUMBERS + SYMBOLS;

	public static String generatePassword(int length) {
		List<String> chars = Arrays.asList(PASSWORD_CHARS.split(""));
		Collections.shuffle(chars, new SecureRandom());
		String password = "";
		for (int i = 0; i < length; i++) {
			password += chars.get(i);
		}
		return password;
	}
}