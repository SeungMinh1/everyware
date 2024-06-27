package com.yedam.app.calendar.service;

import lombok.Data;

@Data
public class CalendarBoxVO {
	private Integer calendarBoxId;
	private String calendarBoxName;
	private Integer empId;
	private String color;
	private int expressionYn;
	private int agreementYn;
	private String empName;
	private int publicYn;
}
