package com.yedam.app.approval.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.DraftMapper;
import com.yedam.app.approval.service.ApprovalVO;
import com.yedam.app.approval.service.DraftService;
import com.yedam.app.approval.service.DraftVO;

@Service
public class DraftServiceImpl implements DraftService{
	@Autowired
	DraftMapper draftMapper;

	@Override
	public DraftVO waitDocList(DraftVO draftVO) {
		
		return draftMapper.waitDocList(draftVO);
	}

	@Override
	public DraftVO waitRefViewDocList(DraftVO draftVO) {
		
		return draftMapper.waitRefViewDocList(draftVO);
	}

	@Override
	public DraftVO expectedDocList(DraftVO draftVO) {
		
		return draftMapper.expectedDocList(draftVO);
	}

	@Override
	public DraftVO draftDocList(DraftVO draftVO) {
		
		return draftMapper.draftDocList(draftVO);
	}

	@Override
	public DraftVO approvalDocList(DraftVO draftVO) {
		
		return draftMapper.approvalDocList(draftVO);
	}

	@Override
	public DraftVO sendDocList(DraftVO draftVO) {
		
		return draftMapper.sendDocList(draftVO);
	}

	@Override
	public DraftVO receptionDocList(DraftVO draftVO) {
		
		return draftMapper.receptionDocList(draftVO);
	}

	@Override
	public DraftVO refViewDocList(DraftVO draftVO) {
		
		return draftMapper.refViewDocList(draftVO);
	}
	
	@Override
	public DraftVO refDocList(DraftVO draftVO) {
		
		return draftMapper.refDocList(draftVO);
	}

	@Override
	public DraftVO viewDocList(DraftVO draftVO) {
		
		return draftMapper.viewDocList(draftVO);
	}

	@Override
	public DraftVO temporaryDocList(DraftVO draftVO) {
		
		return draftMapper.temporaryDocList(draftVO);
	}

	@Override
	public int docInsert(DraftVO draftVO) {
		
		return draftMapper.docInsert(draftVO);
	}

	@Override
	public Map<String, Object> docUpdate(DraftVO draftVO) {
		Map<String, Object> docUpdate = new HashMap<>();
		boolean isSuccessed = false;
		int result = draftMapper.docUpdate(draftVO);
		if(result == 1) {
			isSuccessed = true;
		}
		docUpdate.put("result", isSuccessed);
		docUpdate.put("target", draftVO);
		return docUpdate;
	}

	@Override
	public int docDelete(int docId) {
		
		return draftMapper.docDelete(docId);
	}

	@Override
	public Map<String, Object> draftInsert(DraftVO draftVO) {
		Map<String, Object> draftInsert = new HashMap<>();
		boolean isSuccessed = false;
		int result = draftMapper.draftInsert(draftVO);
		if(result == 1) {
			isSuccessed = true;
		}
		draftInsert.put("result", isSuccessed);
		draftInsert.put("target", draftVO);
		return draftInsert;
	}

	@Override
	public int approvalInsert(ApprovalVO approvalVO) {
		
		return draftMapper.approvalInsert(approvalVO);
	}

	@Override
	public int jobInsert(ApprovalVO approvalVO) {
		
		return draftMapper.jobInsert(approvalVO);
	}

	@Override
	public int refInsert(ApprovalVO approvalVO) {
		
		return draftMapper.refInsert(approvalVO);
	}

	@Override
	public int viewInsert(ApprovalVO approvalVO) {
		
		return draftMapper.viewInsert(approvalVO);
	}

	@Override
	public int fileInsert(DraftVO draftVO) {
		
		return draftMapper.fileInsert(draftVO);
	}
	
	
}
