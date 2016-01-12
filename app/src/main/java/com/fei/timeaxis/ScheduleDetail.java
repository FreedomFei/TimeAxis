package com.fei.timeaxis;

public class ScheduleDetail {

	private String id;
	private boolean available;
	private int level;
	private String subject;
	private String time;

	public ScheduleDetail(String id, boolean available, int level, String subject, String time) {
		super();
		this.id = id;
		this.available = available;
		this.level = level;
		this.subject = subject;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
