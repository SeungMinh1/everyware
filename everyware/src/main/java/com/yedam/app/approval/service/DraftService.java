package com.yedam.app.approval.service;

public interface DraftService {
	// 기안
	public int draftInsert(DraftVO draftVO);

	// 기안문서조회
	public DraftVO draftInfo(DraftVO draftVO);
}
