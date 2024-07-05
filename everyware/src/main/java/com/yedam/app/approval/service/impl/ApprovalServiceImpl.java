package com.yedam.app.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.ApprovalMapper;
import com.yedam.app.approval.mapper.DocMapper;
import com.yedam.app.approval.service.ApprovalService;
import com.yedam.app.approval.service.ApprovalVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	@Autowired
	ApprovalMapper approvalMapper;
	
	@Autowired
	DocMapper docMapper;

	@Override
	public int approvalInsert(ApprovalVO approvalVO) {
		int result = 0;

		List<ApprovalVO> list = approvalVO.getApprovalEmpList();
		for(ApprovalVO approval : list) {
			int order = docMapper.posCode(approval.getApprovalPosition());
			approval.setApprovalEmpOrder(order);
		}
				
		// 결재자 순서
		if(approvalVO.getApprovalOrder().equals("ser")) {
			for(ApprovalVO approval : list) {
				approval.setApprovalType("결재예정");
			}
			list.get(list.size()-1).setApprovalType("결재대기");
		} else {
			for(ApprovalVO approval : list) {
				approval.setApprovalType("결재대기");
			}
		}
		
		for(ApprovalVO approval : list) {
			result += approvalMapper.approvalInsert(approval);
		}
		return result;
	}
	
}
