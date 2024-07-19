package com.yedam.app.calendar.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CalendarVO {
	private Integer calendarId;
	
	private String start;
	
	private String end;
	private String title;
	private String content;
	private String color;
	private Integer empId;
	private Integer calendarBoxId;
	private int expressionYn;
	private String location;
	private String agreementYn;
	private int publicYn;
}
