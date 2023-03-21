package com.event.java.model;

import java.io.Serializable;

public class Comment implements Serializable{
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getUser_cmt() {
		return user_cmt;
	}
	public void setUser_cmt(String user_cmt) {
		this.user_cmt = user_cmt;
	}
	public String getEvent_cmt() {
		return event_cmt;
	}
	public void setEvent_cmt(String event_cmt) {
		this.event_cmt = event_cmt;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	String comment_id ;
	String comment_content, comment_date,user_cmt, event_cmt;
	public Comment() {
		super();
	}
	public Comment(String comment_id, String user_cmt, String event_cmt, String comment_content, String comment_date) {
		super();
		this.comment_id = comment_id;
		this.user_cmt = user_cmt;
		this.event_cmt = event_cmt;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
	}
	
	
}
