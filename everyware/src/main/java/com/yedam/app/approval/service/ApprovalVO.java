package com.yedam.app.approval.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ApprovalVO {
	private int approvalId;
	private String approvalType;
	private String approvalStatus;
	private String approvalOrder;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalDate;
	private String approvalEmp;
	private String approvalRef;
	private String approvalView;
}
