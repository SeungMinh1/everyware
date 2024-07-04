package com.yedam.app.approval.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DraftVO {
	private int draftId;
	private String draftEmp;
	private String draftEmpDept;
	private String draftTitle;
	private String draftContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date draftDate;
	private String draftStatus;
}
