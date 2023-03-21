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

@Service("commentService")
@Transactional
public class CommentService {

	static String db_name = "EventManagement", db_collection = "Comment";
	private static Logger log = Logger.getLogger(CommentService.class);
	public static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	// Fetch all Comments from the mongo database.
	public static List getAll() {
		List<Comment> Comment_list = (List<Comment>) new ArrayList();

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Comment Comment = new Comment();
			Comment.setComment_id(dbObject.get("comment_id").toString());
			Comment.setUser_cmt(dbObject.get("name").toString());
			Comment.setComment_date(dbObject.get("comment_date").toString());
			Comment.setEvent_cmt(dbObject.get("phonenum").toString());
			Comment.setComment_content(dbObject.get("comment_content").toString());

			// Adding the Comment details to the list.
			Comment_list.add(Comment);
		}
		log.debug("Total records fetched from the mongo database are= " + Comment_list.size());
		return Comment_list;
	}

	// Add a new Comment to the mongo database.
	public Boolean add(Comment Comment) {
		boolean output = false;
		Random ran = new Random();
		log.debug(
				"Adding a new Comment to the mongo database; Entered Comment_name is= " + Comment.getComment_content());
		try {
			// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new Comment details to this object.
			BasicDBObject doc = new BasicDBObject();

			doc.put("comment_id", Comment.getComment_id());
			doc.put("user_cmt", Comment.getUser_cmt());
			doc.put("event_cmt", Comment.getEvent_cmt());
			doc.put("comment_date", Comment.getComment_date());
			doc.put("comment_content", Comment.getComment_content());

			// Save a new Comment to the mongo collection.
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new Comment to the mongo database", e);
		}
		return output;
	}

	// Update the selected Comment in the mongo database.
	public Boolean edit(Comment Comment) {
		boolean output = false;
		log.debug("Updating the existing Comment in the mongo database; Entered Comment_id is= " + Comment.getComment_id());
		try {
			// Fetching the Comment details.
			BasicDBObject existing = (BasicDBObject) getDBObject(Comment.getComment_id());

			// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("comment_id", Comment.getComment_id());
			edited.put("user_cmt", Comment.getUser_cmt());
			edited.put("event_cmt", Comment.getEvent_cmt());
			edited.put("comment_date", Comment.getComment_date());
			edited.put("comment_content", Comment.getComment_content());

			// Update the existing Comment to the mongo database.
			coll.update(existing, edited);
			// output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error has occurred while updating an existing Comment to the mongo database", e);
		}
		return output;
	}

	// Delete a Comment from the mongo database.

	public boolean delete(String id) {
	    boolean output = false;
	    log.debug("Deleting an existing Comment from the mongo database; Entered Comment_id is= " + id);
	    try {
	        // Lấy phần tử cần xóa từ cơ sở dữ liệu MongoDB.
	        BasicDBObject query = new BasicDBObject();
	        query.put("_id", new ObjectId(id));

	        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

	        // Xóa phần tử đã chọn từ cơ sở dữ liệu MongoDB.
	        coll.remove(query);
	        output = true;
	    } catch (Exception e) {
	        output = false;
	        log.error("An error occurred while deleting an existing Comment from the mongo database", e);
	    }
	    return output;
	}


	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		// DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected Comment_id to search.
		where_query.put("id", id);
		return coll.findOne(where_query);
	}

	// Fetching a single Comment details from the mongo database.
	public static Comment findCommentId(String id) {
		Comment u = new Comment();

		DBObject where_query = new BasicDBObject();
		where_query.put("id", id);

		DBObject dbo = coll.findOne(where_query);
		u.setComment_id(dbo.get("comment_id").toString());
		u.setUser_cmt(dbo.get("user_cmt").toString());
		u.setEvent_cmt(dbo.get("event_cmt").toString());
		u.setComment_date(dbo.get("comment_date").toString());
		u.setComment_content(dbo.get("comment_content").toString());

		// Return Comment object.
		return u;
	}

	public static Comment find(String id) {
		List<Comment> Comment_list = getAll();

		for (Comment Comment : Comment_list) {
			if (Comment.getComment_id().equalsIgnoreCase(id)) {
				return Comment;
			}
		}
		return null;
	}

}
