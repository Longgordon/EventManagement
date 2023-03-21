package com.event.java.model;

public class Like {
	String user_like, event_like;

	public String getUser_like() {
		return user_like;
	}

	public void setUser_like(String user_like) {
		this.user_like = user_like;
	}

	public String getEvent_like() {
		return event_like;
	}

	public void setEvent_like(String event_like) {
		this.event_like = event_like;
	}

	public Like() {
        super();
    }

    public Like(String user_like,String event_like) {
        super();
        this.user_like= user_like;
        this.event_like=event_like;    }

	
}
