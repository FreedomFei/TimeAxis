package com.fei.timeaxis;

import java.util.List;

public class Schedule {

	private String date;
	private List<ScheduleDetail> list;

	public Schedule(String date, List<ScheduleDetail> list) {
		super();
		this.date = date;
		this.list = list;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ScheduleDetail> getList() {
		return list;
	}

	public void setList(List<ScheduleDetail> list) {
		this.list = list;
	}

}
