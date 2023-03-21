package com.event.java.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.event.java.model.EventService;
import com.event.java.model.MongoFactory;
import com.event.java.model.UserE;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/////////////////////////////////////
import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.event.java.model.Event;
import com.event.java.model.EventService;

@Controller
@RequestMapping("/event")
public class EventController {

	private static final String DB_NAME = "EventManagement";
	private static final String DB_COLLECTION = "Event";
	private static final String DB_COLLECTION1 = "User";
	private static final Logger LOG = Logger.getLogger(EventController.class);
	DBCollection coll_event = MongoFactory.getCollection(DB_NAME, DB_COLLECTION);
	DBCollection coll_user = MongoFactory.getCollection(DB_NAME, DB_COLLECTION1);
	@Resource(name = "eventService")
	public EventService EventService;
	@Resource(name = "userEService")
	public EventService UserEService;

	@GetMapping("/list")
	public String getAllEvents(Model model, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "12") int pageSize) {
		LOG.debug("Request to fetch all Events from the mongo database");
		List<Event> EventList = EventService.getAll(pageNum, pageSize);
		int EventCount = EventService.getEventCount();
		int totalPages = (int) Math.ceil((double) EventCount / pageSize);

		List<Integer> pages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			pages.add(i);
		}
		model.addAttribute("events", EventList);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("pages", pages);
		model.addAttribute("totalPages", totalPages);

		return "listevent";

	}

	
	@RequestMapping(value = "createevent", method = RequestMethod.GET)
	public String regis(Model model) {
		model.addAttribute("createForm", new Event());
		return "create_event";
	}

	@RequestMapping(value = "createevent", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("createForm") Event createForm, BindingResult result,
			ModelMap modelMap, HttpSession session) {
		UserE checklogin = (UserE) session.getAttribute("loginsession");
		String event_id = createForm.getEvent_id();
		String name = createForm.getName();
		String img1 = createForm.getImg1();
		String img2 = createForm.getImg2();
		String img3 = createForm.getImg3();
		String img4 = createForm.getImg4();
		String start = createForm.getEvent_start();
		String end = createForm.getEvent_end();
		int join = createForm.getJoin();
		String host = createForm.getHost();
		int userid = Integer.parseInt(checklogin.getId());
		String note = createForm.getNote();
		String place = createForm.getPlace();
		String link = createForm.getDonate_link();
		String status = createForm.getStatus();
		String category = createForm.getName();

		if (result.hasErrors()) {
			modelMap.put("error", "Vui lòng nhập đầy đủ thông tin!");
			return "create_event";
		}

		try {
			DBObject where_query = new BasicDBObject();
			where_query.put("event_id", event_id.toString());
			DBObject dbo = coll_event.findOne(where_query);
			if (dbo != null) {
				modelMap.put("error", "Đã tồn tại email hoặc tài khoản này trong hệ thống!");
				return "create_event";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String id = "";
		DBCursor cursor = coll_event.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			id = dbObject.get("event_id").toString();
		}

		Integer temp = Integer.parseInt(id) + 1;
		BasicDBObject doc = new BasicDBObject();
		doc.put("event_id", Integer.toString(temp));
		doc.put("event_name", name.toString());
		if (img1.isEmpty() || img2.isEmpty() || img3.isEmpty() || img4.isEmpty()) {
			doc.put("event_img1", "https://tse3.mm.bing.net/th?id=OIP.3IsXMskZyheEWqtE3Dr7JwHaGe&pid=Api&P=0");
			doc.put("event_img2", "https://tse3.mm.bing.net/th?id=OIP.3IsXMskZyheEWqtE3Dr7JwHaGe&pid=Api&P=0");
			doc.put("event_img3", "https://tse3.mm.bing.net/th?id=OIP.3IsXMskZyheEWqtE3Dr7JwHaGe&pid=Api&P=0");
			doc.put("event_img4", "https://tse3.mm.bing.net/th?id=OIP.3IsXMskZyheEWqtE3Dr7JwHaGe&pid=Api&P=0");
		} else {
			doc.put("event_img1", img1.toString());
			doc.put("event_img2", img2.toString());
			doc.put("event_img3", img3.toString());
			doc.put("event_img4", img4.toString());
		}
		doc.put("event_start", start.toString());
		doc.put("event_end", end.toString());
		doc.put("event_join", join);
		doc.put("event_host", host.toString());
		doc.put("event_note", note.toString());
		doc.put("event_place", place.toString());
		doc.put("event_donate_link", link.toString());
		doc.put("status", status.toString());
		doc.put("category", category.toString());
		doc.put("E_user_id", userid);
		coll_event.insert(doc);

		return "event/list";
	}

	@RequestMapping(value = "/inforshop", method = RequestMethod.GET)
	public String inforShop() {

		return "inforshop";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String DetailEvent(@RequestParam(value = "id", required = true) String id, Model model) {
		LOG.debug("Request to open the edit user form page");
		model.addAttribute("eventAttr", EventService.find(id));
		return "details";
	}
//	@RequestMapping(value = "/detail", method = RequestMethod.GET)
//	public ModelAndView DetailEvent() {
//		ModelAndView mav = new ModelAndView("details");
//		mav.setViewName("details");
//		return mav;
//	}

	@GetMapping("/search")
	public String searchEvents(@RequestParam("keyword") String keyword, Model model) {
		List<Event> result = EventService.search(keyword);
		model.addAttribute("result", result);
		return "search-result";
	}

	///////////////////////////////////////////////////////////////////
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List Event_list = EventService.getAll();
		modelMap.put("products", Event_list);
		return "Event";
	}
}
