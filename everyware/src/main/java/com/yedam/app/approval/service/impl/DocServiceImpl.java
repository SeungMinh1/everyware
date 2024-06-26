package com.yedam.app.approval.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.DocMapper;
import com.yedam.app.approval.service.DocService;
import com.yedam.app.approval.service.DocVO;
import com.yedam.app.approval.service.TaskVO;

@Service
public class DocServiceImpl implements DocService {
	@Autowired
	DocMapper docMapper;

	@Override
	public  List<DocVO> waitDocList(int page, int cnt, String dosearch) {
		
		return docMapper.waitDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> waitRefViewDocList(int page, int cnt, String dosearch) {
		
		return docMapper.waitRefViewDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> expectedDocList(int page, int cnt, String dosearch) {
		
		return docMapper.expectedDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> draftDocList(int page, int cnt, String dosearch) {
		
		return docMapper.draftDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> approvalDocList(int page, int cnt, String dosearch) {
		
		return docMapper.approvalDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> sendDocList(int page, int cnt, String dosearch) {
		
		return docMapper.sendDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> receptionDocList(int page, int cnt, String dosearch) {
		
		return docMapper.receptionDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> refViewDocList(int page, int cnt, String dosearch) {
		
		return docMapper.refViewDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> refDocList(int page, int cnt, String dosearch) {
		
		return docMapper.refDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> viewDocList(int page, int cnt, String dosearch) {
		
		return docMapper.viewDocList(page, cnt, dosearch);
	}

	@Override
	public  List<DocVO> temporaryDocList(int page, int cnt, String dosearch) {
		
		return docMapper.temporaryDocList(page, cnt, dosearch);
	}
	
	@Override
	public DocVO docInfo(DocVO docVO) {
		return docMapper.docInfo(docVO);
	}

	@Override
	public List<TaskVO> newTask() {
		return docMapper.newTask();
	}

	@Override
	public List<TaskVO> category() {
		return docMapper.category();
	}

	@Override
	public int docInsert(DocVO docVO) {
		
		return docMapper.docInsert(docVO);
	}

	@Override
	public Map<String, Object> docUpdate(DocVO docVO) {
		Map<String, Object> draftInsert = new HashMap<>();
		boolean isSuccessed = false;
		int result = docMapper.docUpdate(docVO);
		if(result == 1) {
			isSuccessed = true;
		}
		draftInsert.put("result", isSuccessed);
		draftInsert.put("target", docVO);
		return draftInsert;
	}

	@Override
	public int docDelete(int docId) {
		
		return docMapper.docDelete(docId);
	}

	@Override
	public int cntList() {
		return docMapper.cntList();
	}
}
