package com.yedam.app.attend.attend.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AttendVO {
	private Integer attendId;
	private String attendType;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date goTime;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date leaveTime;
	private int workTime;
	private int exceedWorkTime;
	private int outYn;
	private int empId; 
	
	private String goTime2; //문자열
	private String leaveTime2; //문자열
	
	private String departmentName;
	private String departmentId;
	private String posName;
	private String empPosition;
	private String empName;
	
	private int totalwork1;
	private int totalwork2;
	
	private int extrawork1;
	private int extrawork2;
	
	private String workdetail;
	private String workdetail2;
	
	private int monthworkTime;
	private int monthExtraworkTime;
	private String newdate;
}

