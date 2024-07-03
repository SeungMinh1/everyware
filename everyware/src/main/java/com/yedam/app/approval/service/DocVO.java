package com.yedam.app.approval.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DocVO {
	private int docId;
	private String docType;
	private String docTitle;
	private String draftEmp;
	private String draftEmpDept;
	private String docContent;
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
	private String approvalOrder;
	private String approvalEmp;
	private int approvalEmpId;
	private String receptionEmp;
	private int receptionEmpId;
	private String sendEmp;
	private int sendEmpId;
	private String refEmp;
	private int refEmpId;
	private String viewEmp;
	private int viewEmpId;
	private List<String> approvalNameList;
	private List<Integer> approvalIdList;
	private List<String> receptionEmpName;
	private List<Integer> receptionNameList;
	private List<String> refNameList;
	private List<Integer> refIdList;
	private List<String> viewNameList;
	private List<Integer> viewIdList;
}
