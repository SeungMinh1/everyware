package com.yedam.app.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yedam.app.approval.service.DocService;

@Controller
public class DocController {
	@Autowired
	DocService docService;
}
