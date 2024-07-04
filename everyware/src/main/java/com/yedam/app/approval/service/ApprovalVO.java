package com.yedam.app.approval.service;

import java.util.Date;
import java.util.List;

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
	private int approvalEmpId;
	private int approvalEmpOrder;
	private int approvalCompleteEmp;
	private String draftEmp;
	private int docId;
	private String approvalPosition;
	private List<ApprovalVO> approvalEmpList;
}
