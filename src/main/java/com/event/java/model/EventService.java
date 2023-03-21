package com.event.java.model;

import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import com.event.java.model.LikeService;

@Service("eventService")
@Transactional
public class EventService {

	static String db_name = "EventManagement", db_collection = "Event";
	private static Logger log = Logger.getLogger(EventService.class);
	public static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	public static List getAll(int pageNum, int pageSize) {
		List Event_list = new ArrayList();
		int skip = (pageNum - 1) * pageSize; // tính vị trí bắt đầu của trang hiện tại

		DBCursor cursor = coll.find().skip(skip).limit(pageSize);

		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Event Event = new Event();
			Event.setEvent_id(dbObject.get("event_id").toString());
			Event.setImg1(dbObject.get("event_img1").toString());
			Event.setImg2(dbObject.get("event_img1").toString());
			Event.setImg3(dbObject.get("event_img1").toString());
			Event.setImg3(dbObject.get("event_img1").toString());
			Event.setName(dbObject.get("event_name").toString());
			Event.setEvent_start(dbObject.get("event_start").toString());
			Event.setEvent_end(dbObject.get("event_end").toString());
			Event.setJoin(((Integer) dbObject.get("event_join")).intValue());
			Event.setHost(dbObject.get("event_host").toString());
			Event.setNote(dbObject.get("event_note").toString());
			Event.setPlace(dbObject.get("event_place").toString());
			Event.setDonate_link(dbObject.get("event_donate_link").toString());
			Event.setStatus(dbObject.get("event_status").toString());
			Event.setLike(LikeService.countEventLikes(dbObject.get("event_id").toString())+"") ;
			Event_list.add(Event);
		}
		log.debug("Total records fetched from the mongo database are= " + Event_list.size());
		return Event_list;
	}

	// Đếm tổng số sản phẩm có trong bảng
	public static int getEventCount() {
		// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		return (int) coll.count();
	}

	// -------------------------------------------------------------------------------------------------
	// Tìm kiếm hoa có trong db

	/*
	 * public static List<Event> search(String keyword) {
	 * 
	 * List<Event> Events = getAll(); List<Event> result = new ArrayList<>(); for
	 * (Event Event : Events) { if (Event.getName().contains(keyword.toLowerCase())
	 * || Event.getName().toLowerCase().contains(keyword)) { result.add(Event); } }
	 * return result; }
	 */

	// ------------------------------------------------------------------------------------------
	// Services chi tiết sản phẩm có rồi nên không copy vô

	// ---------------------------------------------------------------------------------------------------

	public static List<Event> search(String keyword) {

		List<Event> Events = getAll();
		List<Event> result = new ArrayList<>();
		for (Event Event : Events) {
			if (Event.getName().contains(keyword.toLowerCase()) || Event.getName().toLowerCase().contains(keyword)
					|| Event.getHost().toLowerCase().contains(keyword)
					|| Event.getHost().contains(keyword.toLowerCase())) {
				result.add(Event);
			}
		}
		return result;
	}
	// ------------------------------------------------------------------------------------------
	// Services chi tiết sản phẩm có rồi nên không copy vô

	// ---------------------------------------------------------------------------------------------------

	public static List getAll() {
		List<Event> Event_list = new ArrayList();
		// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Event Event = new Event();
			Event.setEvent_id(dbObject.get("event_id").toString());
			Event.setImg1(dbObject.get("event_img1").toString());
			Event.setImg2(dbObject.get("event_img1").toString());
			Event.setImg3(dbObject.get("event_img1").toString());
			Event.setImg3(dbObject.get("event_img1").toString());
			Event.setName(dbObject.get("event_name").toString());
			Event.setEvent_start(dbObject.get("event_start").toString());
			Event.setEvent_end(dbObject.get("event_end").toString());
			Event.setJoin(((Integer) dbObject.get("event_join")).intValue());
			Event.setHost(dbObject.get("event_host").toString());
			Event.setNote(dbObject.get("event_note").toString());
			Event.setPlace(dbObject.get("event_place").toString());
			Event.setDonate_link(dbObject.get("event_donate_link").toString());
			Event.setStatus(dbObject.get("event_status").toString());
			
			Event_list.add(Event);

			// Adding the user details to the list.

		}

		log.debug("Total records fetched from the mongo database are= " + Event_list.size());
		return Event_list;
	}

	public static String utf8(String temp) throws UnsupportedEncodingException {

		String utf8Description = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
		return utf8Description;
	}

	public static Boolean add(Event Event) {
		boolean output = false;
		try {
			// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new user details to this object.
			DBObject doc = new BasicDBObject();
			doc.put("event_id", Event.getEvent_id());
			doc.put("event_name", utf8(Event.getName()));
			doc.put("event_join", Event.getJoin());
			doc.put("event_img1", Event.getImg1());
			doc.put("event_img2", Event.getImg2());
			doc.put("event_img3", Event.getImg3());
			doc.put("event_img4", Event.getImg4());
			doc.put("event_start", Event.getEvent_start());
			doc.put("event_end", Event.getEvent_end());
			doc.put("event_host", Event.getHost());
			doc.put("event_note", Event.getNote());
			doc.put("event_place", Event.getPlace());
			doc.put("event_donate_link", Event.getDonate_link());
			doc.put("event_status", Event.getStatus());
			//
			// System.out.print(Event.getName());
			//
			System.out.print(doc);
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return output;
	}

	public static Boolean update(Event Event) {
		boolean output = false;
		try {
			// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new user details to this object.
			DBObject doc = new BasicDBObject();
			doc.put("event_id", Event.getEvent_id());
			doc.put("event_name", utf8(Event.getName()));
			doc.put("event_join", Event.getJoin());
			doc.put("event_img1", Event.getImg1());
			doc.put("event_img2", Event.getImg2());
			doc.put("event_img3", Event.getImg3());
			doc.put("event_img4", Event.getImg4());
			doc.put("event_start", Event.getEvent_start());
			doc.put("event_end", Event.getEvent_end());
			doc.put("event_host", Event.getHost());
			doc.put("event_note", Event.getNote());
			doc.put("event_place", Event.getPlace());
			doc.put("event_donate_link", Event.getDonate_link());
			doc.put("event_status", Event.getStatus());
			//
			// System.out.print(Event.getName());
			//

			System.out.print(doc);
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return output;
	}

	public static String generatemaxid() {
		List<Event> Event_list = getAll();

		int max = 0;
		for (Event Event : Event_list) {
			String temp = Event.getEvent_id().substring(2);

			if (Integer.parseInt(temp) > max) {
				max = Integer.parseInt(temp);
			}
		}
		max++;
		if (max < 10) {
			return "FL00" + max;
		} else {
			if (max > 9 && max < 100) {
				return "FL0" + max;
			} else
				return "FL" + max;
		}

	}
	/* gia tham gia su kien ==> bo */
//	public static Event quantityreduce(String id, int reduce) {
//		List<Event> Event_list = getAll();
//
//		for (Event Event : Event_list) {
//			if (Event.getEventid().equals(id)) {
//				Event.setStock(Event.getStock() - reduce);
//				DBObject where_query = new BasicDBObject();
//				where_query.put("Eventid", id);
//				DBObject dbfindupdate = coll.findOne(where_query);
//				DBObject edited = new BasicDBObject();
//				edited.put("Eventid", Event.getEventid());
//				edited.put("name", Event.getName());
//				edited.put("description", Event.getDescription());
//				edited.put("price", Event.getPrice());
//				edited.put("image", Event.getUrl());
//				edited.put("stock", Event.stock);
//				coll.update(dbfindupdate, edited);
//				return Event;
//			}
//		}
//		return null;
//	}

	public static Event find(String id) {
		List<Event> Event_list = getAll();

		for (Event Event : Event_list) {
			if (Event.getEvent_id().equalsIgnoreCase(id)) {
				return Event;
			}
		}
		return null;
	}
}
