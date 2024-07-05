package com.yedam.app.approval.service;

import java.util.List;

import lombok.Data;

@Data
public class SendVO {
	private int sendId;
	private String sendEmp;
	private int sendEmpId;
	private String sendStatus;
	private int docId;
	private String draftEmp;
	private int draftEmpId;
	private List<SendVO> sendEmpList;
}
