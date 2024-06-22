package com.yedam.app.approval.service;

import java.util.Date;

import lombok.Data;

@Data
public class ApprovalVO {
	private int approvalId;
	private String approvalType;
	private String approvalStatus;
	private String approvalOrder;
	private Date approvalDate;
	private String approvalEmp;
	private String approvalRef;
	private String approvalView;
}
