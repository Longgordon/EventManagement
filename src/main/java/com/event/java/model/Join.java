package com.event.java.model;

public class Join {
	String user_join, event_join;

	public String getUser_join() {
		return user_join;
	}

	public void setUser_join(String user_join) {
		this.user_join = user_join;
	}

	public String getEvent_join() {
		return event_join;
	}

	public void setEvent_join(String event_join) {
		this.event_join = event_join;
	}

	public Join() {
        super();
    }

    public Join(String user_join,String event_join) {
        super();
        this.user_join= user_join;
        this.event_join=event_join;    }

	
}
