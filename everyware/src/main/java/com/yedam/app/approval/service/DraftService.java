package com.yedam.app.approval.service;

import java.util.List;

public interface DraftService {
	// 기안
	public int draftInsert(DraftVO draftVO);

	// 기안문서조회
	public DraftVO draftInfo(DraftVO draftVO);
	
	// 문서 양식
	public List<TaskVO> taskList();
}
