package com.yedam.app.approval.service;

import java.util.List;

import lombok.Data;

@Data
public class ViewVO {
	private int viewId;
	private String viewEmp;
	private int viewEmpId;
	private String viewStatus;
	private int docId;
	private String draftEmp;
	private int draftEmpId;
	private List<ViewVO> viewEmpList;
	private List<ViewVO> refViewList;
}
