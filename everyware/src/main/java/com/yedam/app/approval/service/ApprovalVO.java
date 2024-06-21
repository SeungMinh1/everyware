package com.yedam.app.approval.service;

import java.util.Date;

import lombok.Data;

@Data
public class ApprovalVO {
	private int docId;
	private String approvalEmpId;
	private String approvalType;
	private String approvalStatus;
	private String approvalOrder;
	private Date approvalDate;
	private int approvalId;
	private String approvalRef;
	private String approvalView;
}
