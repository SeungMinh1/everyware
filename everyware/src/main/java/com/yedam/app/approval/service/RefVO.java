package com.yedam.app.approval.service;

import java.util.List;

import lombok.Data;

@Data
public class RefVO {
	private int refId;
	private String refEmp;
	private int refEmpId;
	private String refStatus;
	private int docId;
	private String draftEmp;
	private int draftEmpId;
	private List<RefVO> refEmpList;
	private List<RefVO> refViewList;
}
