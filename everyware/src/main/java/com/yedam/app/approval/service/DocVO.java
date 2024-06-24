package com.yedam.app.approval.service;

import java.util.Date;

import lombok.Data;

@Data
public class DocVO {
	private int docID;
	private String docType;
	private String drafrEmp;
	private String draftEmpDept;
	private Date draftDate;
	private String approvalTask;
	private String approvalFile;
	private String approvalStatus;
}
