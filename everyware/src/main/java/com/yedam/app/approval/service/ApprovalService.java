package com.yedam.app.approval.service;

import java.util.Map;

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
	
	// 수정(승인)
	public Map<String, Object> approvalUpdate(ApprovalVO approvalVO);
	
	// 수정(승인)(여러개)
	public int approvalUpdateAll(ApprovalVO approvalVO);
		
	// 수정(반려)
	public Map<String, Object> rejectUpdate(ApprovalVO approvalVO);
		
	// 수정(다음 결재자)
	public Map<String, Object> nextEmpUpdate(ApprovalVO approvalVO);
	
	// 삭제
	public int approvalDelete(ApprovalVO approvalVO);
	
	// 참조확인
	public int refUpdate(RefVO refVO);
		
	// 열람확인
	public int viewUpdate(ViewVO viewVO);
	
	// 접수
	public Map<String, Object> receptionUpdate(ReceptionVO receptionVO);
	public Map<String, Object> sendUpdate(SendVO sendVO);
	
	// 반송
	public Map<String, Object> receptionReturn(ReceptionVO receptionVO);
	public Map<String, Object> sendReturn(SendVO sendVO);
		
	// 접수취소
	public Map<String, Object> receptionCancel(ReceptionVO receptionVO);
	public Map<String, Object> sendCancel(SendVO sendVO);
}
