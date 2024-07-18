package com.yedam.app.approval.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.approval.service.DraftService;
import com.yedam.app.approval.service.DraftVO;
import com.yedam.app.approval.service.TaskVO;

@Controller
public class DraftController {
	@Autowired
	DraftService draftService;
	
	@PostMapping("draftInsert")
	@ResponseBody
	public int draftInsert(@RequestBody DraftVO draftVO) {
		return draftService.draftInsert(draftVO);
	}
	
	@GetMapping("taskList")
	public String taskList(Model model) {
		List<TaskVO> list = draftService.taskList();
		model.addAttribute("taskList", list);
		return "approvalDoc/taskManagement";
	}
}
