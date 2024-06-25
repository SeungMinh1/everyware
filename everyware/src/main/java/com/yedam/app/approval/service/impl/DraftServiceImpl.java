package com.yedam.app.approval.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.DraftMapper;
import com.yedam.app.approval.service.ApprovalVO;
import com.yedam.app.approval.service.DraftService;
import com.yedam.app.approval.service.DraftVO;

@Service
public class DraftServiceImpl implements DraftService{
	@Autowired
	DraftMapper draftMapper;

	@Override
	public Map<String, Object> draftInsert(DraftVO draftVO) {
		Map<String, Object> draftInsert = new HashMap<>();
		boolean isSuccessed = false;
		int result = draftMapper.draftInsert(draftVO);
		if(result == 1) {
			isSuccessed = true;
		}
		draftInsert.put("result", isSuccessed);
		draftInsert.put("target", draftVO);
		return draftInsert;
	}

	@Override
	public int approvalInsert(ApprovalVO approvalVO) {
		
		return draftMapper.approvalInsert(approvalVO);
	}

	@Override
	public int jobInsert(ApprovalVO approvalVO) {
		
		return draftMapper.jobInsert(approvalVO);
	}

	@Override
	public int refInsert(ApprovalVO approvalVO) {
		
		return draftMapper.refInsert(approvalVO);
	}

	@Override
	public int viewInsert(ApprovalVO approvalVO) {
		
		return draftMapper.viewInsert(approvalVO);
	}

	@Override
	public int fileInsert(DraftVO draftVO) {
		
		return draftMapper.fileInsert(draftVO);
	}
	
	
}
