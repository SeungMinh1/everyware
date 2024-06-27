package com.yedam.app.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.approval.service.DraftService;
import com.yedam.app.approval.service.DraftVO;

@Controller
public class DraftController {
	@Autowired
	DraftService draftService;
	
}
