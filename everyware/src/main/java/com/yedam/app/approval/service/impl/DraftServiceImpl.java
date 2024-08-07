package com.yedam.app.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.DraftMapper;
import com.yedam.app.approval.service.DraftService;
import com.yedam.app.approval.service.DraftVO;
import com.yedam.app.approval.service.TaskVO;

@Service
public class DraftServiceImpl implements DraftService{
	@Autowired
	DraftMapper draftMapper;

	@Override
	public int draftInsert(DraftVO draftVO) {
		return draftMapper.draftInsert(draftVO);
	}

	@Override
	public DraftVO draftInfo(DraftVO draftVO) {
		return draftMapper.draftInfo(draftVO);
	}

	@Override
	public List<TaskVO> taskList() {
		return draftMapper.taskList();
	}
}
