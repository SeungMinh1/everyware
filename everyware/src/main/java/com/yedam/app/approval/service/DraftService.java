package com.yedam.app.approval.service;

import java.util.Map;

public interface DraftService {
	// 기안자
	public Map<String, Object> draftInsert(DraftVO draftVO);

	// 결재자등록
	public int approvalInsert(ApprovalVO approvalVO);

	// 업무등록
	public int jobInsert(ApprovalVO approvalVO);

	// 참조자등록
	public int refInsert(ApprovalVO approvalVO);

	// 열람자등록
	public int viewInsert(ApprovalVO approvalVO);

	// 첨부파일등록
	public int fileInsert(DraftVO draftVO);
	
	// 기안문서조회
	public DraftVO draftInfo(DraftVO draftVO);
}
