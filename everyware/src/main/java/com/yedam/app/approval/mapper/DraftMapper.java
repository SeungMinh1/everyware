package com.yedam.app.approval.mapper;

import com.yedam.app.approval.service.DraftVO;

public interface DraftMapper {
		// 기안
		public int draftInsert(DraftVO draftVO);
		
		// 기안문서조회
		public DraftVO draftInfo(DraftVO draftVO);
}
