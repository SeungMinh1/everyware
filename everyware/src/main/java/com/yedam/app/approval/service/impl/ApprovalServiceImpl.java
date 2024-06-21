package com.yedam.app.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.ApprovalMapper;
import com.yedam.app.approval.service.ApprovalService;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	@Autowired
	ApprovalMapper approvalMapper;
}
