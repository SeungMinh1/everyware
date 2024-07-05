package com.yedam.app.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.approval.service.ApprovalService;
import com.yedam.app.approval.service.ApprovalVO;
import com.yedam.app.approval.service.ReceptionVO;
import com.yedam.app.approval.service.RefVO;
import com.yedam.app.approval.service.SendVO;
import com.yedam.app.approval.service.ViewVO;

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
	
	// 수신자 등록(처리)
	@PostMapping("receptionInsert")
	@ResponseBody
	public int receptionInsert(@RequestBody ReceptionVO receptionVO) {
		return approvalService.receptionInsert(receptionVO);
	}

	// 발송자 등록(처리)
	@PostMapping("sendInsert")
	@ResponseBody
	public int sendInsert(@RequestBody SendVO sendVO) {
		return approvalService.sendInsert(sendVO);
	}

	// 참조자 등록(처리)
	@PostMapping("refInsert")
	@ResponseBody
	public int refInsert(@RequestBody RefVO refVO) {
		return approvalService.refInsert(refVO);
	}

	// 열람자 등록(처리)
	@PostMapping("viewInsert")
	@ResponseBody
	public int viewInsert(@RequestBody ViewVO viewVO) {
		return approvalService.viewInsert(viewVO);
	}
}
