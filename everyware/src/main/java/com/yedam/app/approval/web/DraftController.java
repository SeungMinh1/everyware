package com.yedam.app.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.approval.service.DraftService;
import com.yedam.app.approval.service.DraftVO;

@Controller
public class DraftController {
	@Autowired
	DraftService draftService;
	
	@PostMapping("draftInsert")
	@ResponseBody
	public int draftInsert(@RequestBody DraftVO draftVO) {
		return draftService.draftInsert(draftVO);
	}
}
