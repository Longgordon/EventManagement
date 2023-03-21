package com.event.java.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

@Service("userEService")
@Transactional
public class UserEService {

    static String db_name = "EventManagement", db_collection = "User";
    private static Logger log = Logger.getLogger(UserEService.class);
    public static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
    // Fetch all users from the mongo database.
    public static List getAll() {
    	List<UserE> user_list =(List<UserE>) new ArrayList();
       

        // Fetching cursor object for iterating on the database records.
        DBCursor cursor = coll.find();
        while(cursor.hasNext()) {
            DBObject dbObject = cursor.next();

            UserE user = new UserE();
            user.setId(dbObject.get("Userid").toString());
            user.setName(dbObject.get("Username").toString());
            user.setSex(dbObject.get("sex").toString());
            user.setBirth(dbObject.get("BirthDate").toString());
            user.setImg(dbObject.get("Image").toString());
            user.setType(dbObject.get("Types").toString());
            user.setPhonenum(dbObject.get("Numberphone").toString());
            user.setEmail(dbObject.get("Email").toString());

            // Adding the user details to the list.
            user_list.add(user);
        }
        log.debug("Total records fetched from the mongo database are= " + user_list.size());
        return user_list;
    }

    public static String utf8(String temp) throws UnsupportedEncodingException {

		String utf8Description = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
		return utf8Description;
	}
    // Add a new user to the mongo database.
    public Boolean add(UserE user) {
        boolean output = false;
        Random ran = new Random();
        log.debug("Adding a new user to the mongo database; Entered user_name is= " + user.getName());
        try {
           // DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

            // Create a new object and add the new user details to this object.
            BasicDBObject doc = new BasicDBObject();
            
           
            doc.put("Userid", user.getId());
            doc.put("Username", utf8(user.getName()));
            doc.put("Email", user.getEmail());
            doc.put("BirthDate", user.getBirth());
            doc.put("Numberphone", user.getPhonenum());
            doc.put("sex", user.getSex());
            doc.put("Image", user.getImg());
            // Save a new user to the mongo collection.
            coll.insert(doc);
            output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error occurred while saving a new user to the mongo database", e);
        }
        return output;
    }

    // Update the selected user in the mongo database.
    public Boolean edit(UserE user) {
        boolean output = false;
        log.debug("Updating the existing user in the mongo database; Entered user_id is= " + user.getId());
        try {
            // Fetching the user details.
            BasicDBObject existing = (BasicDBObject) getDBObject(user.getId());

           // DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

            // Create a new object and assign the updated details.
            BasicDBObject edited = new BasicDBObject();
            edited.put("Userid", user.getId());
            edited.put("Username", user.getName());
            edited.put("BirthDate", user.getBirth());
            edited.put("Numberphone", user.getPhonenum());
            edited.put("Email", user.getEmail());
            edited.put("sex", user.getSex());
            edited.put("Image", user.getImg());
            // Update the existing user to the mongo database.
            coll.update(existing, edited);
            //output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error has occurred while updating an existing user to the mongo database", e);
        }
        return output;
    }

    // Delete a user from the mongo database.
	/*
	 * public Boolean delete(String id) { boolean output = false; log.
	 * debug("Deleting an existing user from the mongo database; Entered user_id is= "
	 * + id); try { // Fetching the required user from the mongo database.
	 * BasicDBObject item = (BasicDBObject) getDBObject(id);
	 * 
	 * // DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	 * 
	 * // Deleting the selected user from the mongo database. coll.remove(item);
	 * output = true; } catch (Exception e) { output = false; log.
	 * error("An error occurred while deleting an existing user from the mongo database"
	 * , e); } return output; }
	 */

    // Fetching a particular record from the mongo database.
    private DBObject getDBObject(String id) {
     //   DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();

        // Put the selected user_id to search.
        where_query.put("Userid", id);
        return coll.findOne(where_query);
    }

    // Fetching a single user details from the mongo database.
    public static UserE findUserId(String id) {
        UserE u = new UserE();
 
        DBObject where_query = new BasicDBObject();
        where_query.put("Userid", id);

        DBObject dbo = coll.findOne(where_query);
        u.setId(dbo.get("Userid").toString());
        u.setName(dbo.get("Username").toString());
        u.setPhonenum(dbo.get("Numberphone").toString());
        u.setSex(dbo.get("sex").toString());
        u.setEmail(dbo.get("Email").toString());
        u.setPassword(dbo.get("Password").toString());
        u.setImg(dbo.get("Image").toString());
        u.setType(dbo.get("Types").toString());
        // Return user object.
        return u;
    }
    
	   public static UserE find(String id) {
			 List<UserE> user_list = getAll();
			
			for (UserE user : user_list) {
				if (user.getId().equalsIgnoreCase(id)) {
					return user;
				}
			}
			return null;
		}
	   
	   
	  

}
