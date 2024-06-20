package com.yedam.app.attend.attend.service;

import java.util.Date;

import lombok.Data;

@Data
public class AttendVO {
	private Integer attendId;
	private String attendType;
	private Date goTime;
	private Date leaveTime;
	private int workTime;
	private int exceedWorkTime;
	private String outsideYn;
	private int empId; 
}
