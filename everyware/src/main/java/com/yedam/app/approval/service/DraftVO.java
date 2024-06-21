package com.yedam.app.approval.service;

import java.util.Date;

import lombok.Data;

@Data
public class DraftVO {
	private String draftEmp;
	private String draftTitle;
	private String draftContent;
	private Date draftDate;
	private String draftStatus;
}
