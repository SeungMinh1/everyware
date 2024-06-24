package com.yedam.app.calendar.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CalendarVO {
	private Integer calendarId;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh시 mm분")
	private Date start;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh시 mm분")
	private Date end;
	private String title;
	private String content;
	private String color;
	private Integer empId;
	private Integer calendarBoxId;
}
