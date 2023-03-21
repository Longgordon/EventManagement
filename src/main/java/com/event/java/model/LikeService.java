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


@Service("likeService")
@Transactional
public class LikeService {

	static String db_name = "EventManagement", db_collection = "Like";
	private static Logger log = Logger.getLogger(LikeService.class);
	public static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	// Fetch all likes from the mongo database.
	public static List getAll() {
		List<Like> like_list = (List<Like>) new ArrayList();

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Like like = new Like();
			like.setEvent_like(dbObject.get("event_like").toString());
			like.setUser_like(dbObject.get("user_like").toString());

			// Adding the like details to the list.
			like_list.add(like);
		}
		log.debug("Total records fetched from the mongo database are= " + like_list.size());
		return like_list;
	}

	// Add a new like to the mongo database.
	public Boolean add(Like like) {
		boolean output = false;
		Random ran = new Random();
		log.debug(
				"Adding a new like to the mongo database; Entered like_name is= " + like.getEvent_like());
		try {
			// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new like details to this object.
			BasicDBObject doc = new BasicDBObject();

			doc.put("user_like", like.getUser_like());
			doc.put("event_like", like.getEvent_like());

			// Save a new like to the mongo collection.
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new like to the mongo database", e);
		}
		return output;
	}

	// Update the selected like in the mongo database.

	// Delete a like from the mongo database.

	public boolean delete(String id) {
	    boolean output = false;
	    log.debug("Deleting an existing like from the mongo database; Entered like_id is= " + id);
	    try {
	        // Lấy phần tử cần xóa từ cơ sở dữ liệu MongoDB.
	        BasicDBObject query = new BasicDBObject();
	        query.put("event_like", new ObjectId(id));

	        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	        // Xóa phần tử đã chọn từ cơ sở dữ liệu MongoDB.
	        coll.remove(query);
	        output = true;
	    } catch (Exception e) {
	        output = false;
	        log.error("An error occurred while deleting an existing like from the mongo database", e);
	    }
	    return output;
	}
	public static int countEventLikes(String event_id) 
	{
        int numLikes = 0;
        boolean output = false;
	    log.debug("counting an existing like from the mongo database; Entered like_id is= " + event_id);
        try {
		List<Like> listlike= LikeService.getAll();
		for (Like like : listlike) {
			if(like.getEvent_like().equals(event_id)) {
				numLikes++;
			}
		}
        output = true;
		} catch (Exception e) {
			output = false;
	        log.error("An error occurred while counting an existing like from the mongo database", e);
		}
        
        return numLikes;
    }

	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected like_id to search.
		where_query.put("event_like", id);
		return coll.findOne(where_query);
	}

	// Fetching a single like details from the mongo database.
	public static Like findlikeId(String id) {
		Like u = new Like();

		DBObject where_query = new BasicDBObject();
		where_query.put("id", id);

		DBObject dbo = coll.findOne(where_query);
		u.setUser_like(dbo.get("user_like").toString());
		u.setEvent_like(dbo.get("event_like").toString());
		// Return like object.
		return u;
	}

	public static Like find(String id) {
		List<Like> like_list = getAll();

		for (Like like : like_list) {
			if (like.getEvent_like().equalsIgnoreCase(id)) {
				return like;
			}
		}
		return null;
	}

}
