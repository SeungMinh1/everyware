package com.yedam.app.schedule.service;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ScheduleVO {
	private Integer scheduleId;
	private String productionId;
	private String dayNight;
	
	
	//squadEmp
	private int squadNo;
	private int empId;
	private String empPosition;
	private String empName;
	
	//squad
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String startDate;
	private int rotateNum;
	private int rotateCircle;
	private int squadNum; //조 개수
	
	private List<Integer> squadList; //같은 조 리스트
	
	
	
	
	
}
