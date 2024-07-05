package com.yedam.app.approval.service;

public interface ApprovalService {
	// 결재자 등록
	public int approvalInsert(ApprovalVO approvalVO);
	
	// 수신자 등록
	public int receptionInsert(ReceptionVO receptionVO);
	
	// 발송자 등록
	public int sendInsert(SendVO sendVO);
	
	// 참조자 등록
	public int refInsert(RefVO refVO);
	
	// 열람자 등록
	public int viewInsert(ViewVO viewVO);
}
