package com.yedam.app.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yedam.app.approval.service.ApprovalService;

@Controller
public class ApprovalController {
	@Autowired
	ApprovalService approvalService;
}
