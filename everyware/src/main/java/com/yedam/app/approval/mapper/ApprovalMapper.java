package com.yedam.app.approval.mapper;

import com.yedam.app.approval.service.ApprovalVO;

public interface ApprovalMapper {
	// 결재자 등록
	public int approvalInsert(ApprovalVO approvalVO);
	
	// 참조자 등록
	public int refInsert(ApprovalVO approvalVO);
	
	// 열람자 등록
	public int viewInsert(ApprovalVO approvalVO);
}
