package com.yedam.app.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.ApprovalMapper;
import com.yedam.app.approval.service.ApprovalService;
import com.yedam.app.approval.service.ApprovalVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	@Autowired
	ApprovalMapper approvalMapper;

	@Override
	public int approvalInsert(ApprovalVO approvalVO) {
		return approvalMapper.approvalInsert(approvalVO);
	}

	@Override
	public int refInsert(ApprovalVO approvalVO) {
		return approvalMapper.refInsert(approvalVO);
	}

	@Override
	public int viewInsert(ApprovalVO approvalVO) {
		return approvalMapper.viewInsert(approvalVO);
	}
	
	
}
