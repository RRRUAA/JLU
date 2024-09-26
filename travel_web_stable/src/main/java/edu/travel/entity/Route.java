package edu.travel.entity;

import java.util.Date;

//路线
public class Route {

	private int id;
	private String route_no;
	private String name;//酒店名称
	private int u_id;
	private User user;
	private String note;//房间详细介绍
	private String start_spot;
	private String end_spot;
	private Date create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoute_no() {
		return route_no;
	}

	public void setRoute_no(String route_no) {
		this.route_no = route_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStart_spot() {
		return start_spot;
	}

	public void setStart_spot(String start_spot) {
		this.start_spot = start_spot;
	}

	public String getEnd_spot() {
		return end_spot;
	}

	public void setEnd_spot(String end_spot) {
		this.end_spot = end_spot;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


}
