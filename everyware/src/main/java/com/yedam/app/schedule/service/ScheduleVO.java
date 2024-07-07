package com.yedam.app.schedule.service;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleVO {
	private Integer scheduleId;
	private String productionId;
	private String dayNight;
	
	//squadEmp
	private int squadNo;
	private int empId;
	
	//squad
	private Date startDate;
	private int rotateNum;
	private int rotateCircle;
	private int squadNum; //조 개수
	
	
	
	
	
}
