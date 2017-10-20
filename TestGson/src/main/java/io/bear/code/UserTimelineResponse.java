package io.bear.code;

public class UserTimelineResponse {
	String[] statuses;
	String[] marks;
	Boolean hasvisble;
	Integer previous_cursor;
	Integer next_cursor;
	Integer total_number;
	Integer interval;
	Integer uve_blank;
	public UserTimelineResponse(String[] statuses, String[] marks, Boolean hasvisble, Integer previous_cursor,
			Integer next_cursor, Integer total_number, Integer interval, Integer uve_blank) {
		super();
		this.statuses = statuses;
		this.marks = marks;
		this.hasvisble = hasvisble;
		this.previous_cursor = previous_cursor;
		this.next_cursor = next_cursor;
		this.total_number = total_number;
		this.interval = interval;
		this.uve_blank = uve_blank;
	}
	public String[] getStatuses() {
		return statuses;
	}
	public void setStatuses(String[] statuses) {
		this.statuses = statuses;
	}
	public String[] getMarks() {
		return marks;
	}
	public void setMarks(String[] marks) {
		this.marks = marks;
	}
	public Boolean getHasvisble() {
		return hasvisble;
	}
	public void setHasvisble(Boolean hasvisble) {
		this.hasvisble = hasvisble;
	}
	public Integer getPrevious_cursor() {
		return previous_cursor;
	}
	public void setPrevious_cursor(Integer previous_cursor) {
		this.previous_cursor = previous_cursor;
	}
	public Integer getNext_cursor() {
		return next_cursor;
	}
	public void setNext_cursor(Integer next_cursor) {
		this.next_cursor = next_cursor;
	}
	public Integer getTotal_number() {
		return total_number;
	}
	public void setTotal_number(Integer total_number) {
		this.total_number = total_number;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public Integer getUve_blank() {
		return uve_blank;
	}
	public void setUve_blank(Integer uve_blank) {
		this.uve_blank = uve_blank;
	}
	
	
}
