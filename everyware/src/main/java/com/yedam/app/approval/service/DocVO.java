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
	private int draftEmpId;
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
	private String approvalEmpId;
	private String receptionEmp;
	private String receptionEmpId;
	private String sendEmp;
	private String sendEmpId;
	private String refEmp;
	private String refEmpId;
	private String viewEmp;
	private String viewEmpId;
	private List<String> approvalNameList;
	private List<String> approvalIdList;
	private List<String> receptionNameList;
	private List<String> receptionIdList;
	private List<String> refNameList;
	private List<String> refIdList;
	private List<String> viewNameList;
	private List<String> viewIdList;
	private List<String> sendNameList;
	private List<String> sendIdList;
	private String docInfo;
	private String enforceDate;
	private String search;
	private String approvalInfo;
	private List<String> approvalFileList;
	private String dosearch;
	private String searchOption;
}
