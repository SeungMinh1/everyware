package com.yedam.app.approval.service;

import java.util.List;

import lombok.Data;

@Data
public class TaskVO {
	private String approvalTask;
	private String taskGraph;
	private String taksText;
	private String taskFile;
	private String taskCheck;
	private String taskSelect;
	private String taskDate;
	private String category;
	private String taskDocPath;
	private List<TaskVO> taskList;
}
