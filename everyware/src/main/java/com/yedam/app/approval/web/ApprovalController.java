package com.yedam.app.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.approval.service.ApprovalService;
import com.yedam.app.approval.service.ApprovalVO;

@Controller
public class ApprovalController {
	@Autowired
	ApprovalService approvalService;
	
	// 결재자 등록(처리)
	@PostMapping("approvalInsert")
	@ResponseBody
	public int approvalInsert(@RequestBody ApprovalVO approvalVO) {
		return approvalService.approvalInsert(approvalVO);
	}
}
