package com.yedam.app.approval.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DocVO {
	private int docId;
	private String docType;
	private String docTitle;
	private String draftEmp;
	private String draftEmpDept;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date draftDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date completeDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sendDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date receptionDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String approvalTask;
	private String approvalFile;
	private String approvalStatus;
	private String approvalEmp;
	private String receptionEmp;
	private String sendEmp;
	private String refEmp;
	private String viewEmp;
}
