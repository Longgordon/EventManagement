package com.event.java.model;

import java.io.Serializable;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	String img1, img2, img3, img4,event_id, name, event_start, event_end, host, note, place, donate_link , status,like;
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getImg4() {
		return img4;
	}
	public void setImg4(String img4) {
		this.img4 = img4;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEvent_start() {
		return event_start;
	}
	public void setEvent_start(String event_start) {
		this.event_start = event_start;
	}
	public String getEvent_end() {
		return event_end;
	}
	public void setEvent_end(String event_end) {
		this.event_end = event_end;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDonate_link() {
		return donate_link;
	}
	public void setDonate_link(String donate_link) {
		this.donate_link = donate_link;
	}
	public int getJoin() {
		return join;
	}
	public void setJoin(int join) {
		this.join = join;
	}
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	int join, user_id;
	
	public Event(){
    	super();
    }
	public Event(String event_id,String img1, String img2, String img3, String img4, String name, String event_start, String event_end,
			String host, String note, String place, String donate_link, int join, int user_id, String status, String like) {
		super();
		this.like=like;
		this.event_id= event_id;
		this.status = status;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.name = name;
		this.event_start = event_start;
		this.event_end = event_end;
		this.host = host;
		this.note = note;
		this.place = place;
		this.donate_link = donate_link;
		this.join = join;
		this.user_id = user_id;
	}
}
