package com.travel.story.entity;

public class Site {
	
	int id;
	int starInt; // from 0 to 5
	String name;
	String introduce;
	String phone;
	String ticket;
	String openTime;
	String traffic;
	String type;
	String[] pics; 
	
	static String[] k = {};
	
	public Site() {
        this(1,1, "", "", "", "", "","","", k);
    }
	
	public Site(int id, int starInt, String name, String introduce, String phone, String ticket, String openTime, String traffic, String type, String[] pics){
        
    	this.id = id;
    	this.starInt = starInt;
        this.name = name;
        this.introduce = introduce;
        this.phone = phone;
        this.ticket = ticket;
        this.openTime = openTime;
        this.traffic = traffic;
        this.type = type;
        this.pics = pics;
    }
	
	public int getId() {
        return id;
    }
	
	public int getStarInt() {
		return starInt;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public String getOpenTime() {
		return openTime;
	}
	
	public String getTraffic() {
		return traffic;
	}
	
	public String getType() {
		return type;
	}
	
	public String[] getPics(){
		return pics;
	}
}
