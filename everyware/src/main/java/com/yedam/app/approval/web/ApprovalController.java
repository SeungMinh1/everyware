package com.yedam.app.approval.web;

import java.util.Map;

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
	
	// 수정(승인)
	@PostMapping("approvalUpdate")
	@ResponseBody
	public Map<String, Object> approvalUpdate(@RequestBody ApprovalVO approvalVO) {
		return approvalService.approvalUpdate(approvalVO);
	}
	
	// 수정(승인)(여러개)
	@PostMapping("approvalUpdateAll")
	@ResponseBody
	public int approvalUpdateAll(@RequestBody ApprovalVO approvalVO) {
		return approvalService.approvalUpdateAll(approvalVO);
	}
	
	// 수정(반려)
	@PostMapping("rejectUpdate")
	@ResponseBody
	public Map<String, Object> rejectUpdate(@RequestBody ApprovalVO approvalVO) {
		return approvalService.rejectUpdate(approvalVO);
	}
	
	// 수정(다음 결재자)
	@PostMapping("nextEmpUpdate")
	@ResponseBody
	public Map<String, Object> nextEmpUpdate(@RequestBody ApprovalVO approvalVO) {
		return approvalService.nextEmpUpdate(approvalVO);
	}
	
	// 삭제
	@PostMapping("approvalDelete")
	@ResponseBody
	public Map<String, Object> approvalDelete(@RequestBody ApprovalVO approvalVO) {
		return approvalService.nextEmpUpdate(approvalVO);
	}
	
	
	// 참조확인
	@PostMapping("refUpdate")
	@ResponseBody
	public int refUpdate(@RequestBody RefVO refVO) {
		return approvalService.refUpdate(refVO);
	}
	
	// 열람확인
	@PostMapping("viewUpdate")
	@ResponseBody
	public int viewUpdate(@RequestBody ViewVO viewVO) {
		return approvalService.viewUpdate(viewVO);
	}
	
	// 접수
	@PostMapping("receptionUpdate")
	@ResponseBody
	public Map<String, Object> receptionUpdate(@RequestBody ReceptionVO receptionVO) {
		return approvalService.receptionUpdate(receptionVO);
	}
	
	@PostMapping("sendUpdate")
	@ResponseBody
	public Map<String, Object> sendUpdate(@RequestBody SendVO sendVO) {
		return approvalService.sendUpdate(sendVO);
	}
	
	// 반송
	@PostMapping("receptionReturn")
	@ResponseBody
	public Map<String, Object> receptionReturn(@RequestBody ReceptionVO receptionVO) {
		return approvalService.receptionReturn(receptionVO);
	}
	
	@PostMapping("sendReturn")
	@ResponseBody
	public Map<String, Object> sendReturn(@RequestBody SendVO sendVO) {
		return approvalService.sendReturn(sendVO);
	}
	
	// 접수취소
	@PostMapping("receptionCancel")
	@ResponseBody
	public Map<String, Object> receptionCancel(@RequestBody ReceptionVO receptionVO) {
		return approvalService.receptionCancel(receptionVO);
	}
	
	@PostMapping("sendCancel")
	@ResponseBody
	public Map<String, Object> sendCancel(@RequestBody SendVO sendVO) {
		return approvalService.sendCancel(sendVO);
	}
}
