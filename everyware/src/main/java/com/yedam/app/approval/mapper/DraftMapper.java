package com.yedam.app.approval.mapper;

import com.yedam.app.approval.service.ApprovalVO;
import com.yedam.app.approval.service.DraftVO;

public interface DraftMapper {
		// 문서조회
			// 결재 대기 문서
			public DraftVO waitDocList(DraftVO draftVO);
			// 참조/열람 대기 문서
			public DraftVO waitRefViewDocList(DraftVO draftVO);
			// 결재 예정 문서
			public DraftVO expectedDocList(DraftVO draftVO);
			// 기안 문서
			public DraftVO draftDocList(DraftVO draftVO);
			// 결재 문서
			public DraftVO approvalDocList(DraftVO draftVO);
			// 발송 문서
			public DraftVO sendDocList(DraftVO draftVO);
			// 수신 문서
			public DraftVO receptionDocList(DraftVO draftVO);
			// 참조/열람 문서
			public DraftVO refViewDocList(DraftVO draftVO);
				// 참조 문서
				public DraftVO refDocList(DraftVO draftVO);	
				// 열람 문서
				public DraftVO viewDocList(DraftVO draftVO);
			// 임시 문서
			public DraftVO temporaryDocList(DraftVO draftVO);
		// 문서등록
		public int docInsert(DraftVO draftVO);
		// 문서수정
		public int docUpdate(DraftVO draftVO);
		// 문서삭제
		public int docDelete(int docId);
		//-------------------------기안--------------------------
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
