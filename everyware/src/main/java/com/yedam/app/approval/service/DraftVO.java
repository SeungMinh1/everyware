package com.yedam.app.approval.service;

import java.util.Date;

import lombok.Data;

@Data
public class DraftVO {
	private int docId;
	private int docType;
	private String draftTitle;
	private String draftContent;
	private Date wirteDate;
	private int empId;
	private int draftStatus;
	private String attachmentGroupId;
}
