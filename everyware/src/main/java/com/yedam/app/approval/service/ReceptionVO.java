package com.yedam.app.approval.service;

import java.util.List;

import lombok.Data;

@Data
public class ReceptionVO {
	private int receptionId;
	private String receptionEmp;
	private int receptionEmpId;
	private String receptionStatus;
	private int docId;
	private String draftEmp;
	private int draftEmpId;
	private List<ReceptionVO> receptionEmpList;
	private int sendEmpId;
}
