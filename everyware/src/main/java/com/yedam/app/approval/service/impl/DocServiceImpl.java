package com.yedam.app.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.DocMapper;
import com.yedam.app.approval.service.DocService;

@Service
public class DocServiceImpl implements DocService {
	@Autowired
	DocMapper docMapper;
}
