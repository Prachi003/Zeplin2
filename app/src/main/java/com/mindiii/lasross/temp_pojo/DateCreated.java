package com.mindiii.lasross.temp_pojo;

public class DateCreated{
	private String date;
	private String timezone;
	private int timezoneType;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setTimezoneType(int timezoneType){
		this.timezoneType = timezoneType;
	}

	public int getTimezoneType(){
		return timezoneType;
	}

	@Override
 	public String toString(){
		return 
			"DateCreated{" + 
			"date = '" + date + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",timezone_type = '" + timezoneType + '\'' + 
			"}";
		}
}
