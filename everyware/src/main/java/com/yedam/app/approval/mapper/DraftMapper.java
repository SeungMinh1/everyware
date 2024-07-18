package com.yedam.app.approval.mapper;

import java.util.List;

import com.yedam.app.approval.service.DraftVO;
import com.yedam.app.approval.service.TaskVO;

public interface DraftMapper {
		// 기안
		public int draftInsert(DraftVO draftVO);
		
		// 기안문서조회
		public DraftVO draftInfo(DraftVO draftVO);
		
		// 문서 양식
		public List<TaskVO> taskList();
}
