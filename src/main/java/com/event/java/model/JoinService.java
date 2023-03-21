package com.event.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

@Service("joinService")
@Transactional
public class JoinService {

	static String db_name = "EventManagement", db_collection = "Join";
	
	private static Logger log = Logger.getLogger(JoinService.class);
	public static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	// Fetch all Joins from the mongo database.
	public static List getAll() {
		List<Join> Join_list = (List<Join>) new ArrayList();

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Join Join = new Join();
			Join.setEvent_join(dbObject.get("event_join").toString());
			Join.setUser_join(dbObject.get("user_join").toString());

			// Adding the Join details to the list.
			Join_list.add(Join);
		}
		log.debug("Total records fetched from the mongo database are= " + Join_list.size());
		return Join_list;
	}
	
	// Add a new Join to the mongo database.
	public Boolean add(Join Join) {
		boolean output = false;
		Random ran = new Random();
		log.debug(
				"Adding a new Join to the mongo database; Entered Join_name is= " + Join.getEvent_join());
		try {
			// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new Join details to this object.
			BasicDBObject doc = new BasicDBObject();

			doc.put("user_join", Join.getUser_join());
			doc.put("event_join", Join.getEvent_join());

			// Save a new Join to the mongo collection.
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new Join to the mongo database", e);
		}
		return output;
	}

	// Update the selected Join in the mongo database.

	// Delete a Join from the mongo database.

	public boolean delete(String id) {
	    boolean output = false;
	    log.debug("Deleting an existing Join from the mongo database; Entered Join_id is= " + id);
	    try {
	        // Lấy phần tử cần xóa từ cơ sở dữ liệu MongoDB.
	        BasicDBObject query = new BasicDBObject();
	        query.put("event_join", new ObjectId(id));

	        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	        // Xóa phần tử đã chọn từ cơ sở dữ liệu MongoDB.
	        coll.remove(query);
	        output = true;
	    } catch (Exception e) {
	        output = false;
	        log.error("An error occurred while deleting an existing Join from the mongo database", e);
	    }
	    return output;
	}


	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected Join_id to search.
		where_query.put("event_join", id);
		return coll.findOne(where_query);
	}

	// Fetching a single Join details from the mongo database.
	// Người tạo sự kiện xem những người đã đăng ký sự kiện của mình
	public static Join findJoinId_mem(String id) {
		Join u = new Join();

		DBObject where_query = new BasicDBObject();
		where_query.put("event_join", id);

		DBObject dbo = coll.findOne(where_query);
		u.setUser_join(dbo.get("user_join").toString());
		u.setEvent_join(dbo.get("event_join").toString());
		// Return Join object.
		return u;
	}

	public static Join find_mem(String id) {
		List<Join> Join_list = getAll();

		for (Join Join : Join_list) {
			if (Join.getEvent_join().equalsIgnoreCase(id)) {
				return Join;
			}
		}
		return null;
	}
	// Người dùng xem các event mà mình đã đăng ký tham gia.
		public static Join findJoinId_ad(String id) {
			Join u = new Join();

			DBObject where_query = new BasicDBObject();
			where_query.put("user_join", id);

			DBObject dbo = coll.findOne(where_query);
			u.setUser_join(dbo.get("user_join").toString());
			u.setEvent_join(dbo.get("event_join").toString());
			// Return Join object.
			return u;
		}

		public static Join find_ad(String id) {
			List<Join> Join_list = getAll();

			for (Join Join : Join_list) {
				if (Join.getUser_join().equalsIgnoreCase(id)) {
					return Join;
				}
			}
			return null;
		}

}
