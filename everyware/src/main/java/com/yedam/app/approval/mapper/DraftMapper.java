package com.yedam.app.approval.mapper;

import com.yedam.app.approval.service.ApprovalVO;
import com.yedam.app.approval.service.DraftVO;

public interface DraftMapper {
		// 기안자등록
		public int draftInsert(DraftVO draftVO);
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
}
