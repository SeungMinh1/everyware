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
import com.yedam.app.attend.emp.service.EmpVO;

@Service
public class DocServiceImpl implements DocService {
	@Autowired
	DocMapper docMapper;

	@Override
	public  List<DocVO> waitDocList(int id) {
		
		return docMapper.waitDocList(id);
	}

	@Override
	public  List<DocVO> waitRefViewDocList(int id) {
		
		return docMapper.waitRefViewDocList(id);
	}

	@Override
	public  List<DocVO> expectedDocList(int id) {
		
		return docMapper.expectedDocList(id);
	}

	@Override
	public  List<DocVO> draftDocList(int id) {
		
		return docMapper.draftDocList(id);
	}

	@Override
	public  List<DocVO> approvalDocList(int id) {
		
		return docMapper.approvalDocList(id);
	}

	@Override
	public  List<DocVO> sendDocList(int id) {
		
		return docMapper.sendDocList(id);
	}

	@Override
	public  List<DocVO> receptionDocList(int id) {
		
		return docMapper.receptionDocList(id);
	}

	@Override
	public  List<DocVO> refViewDocList(int id) {
		
		return docMapper.refViewDocList(id);
	}

	@Override
	public  List<DocVO> refDocList(int id) {
		
		return docMapper.refDocList(id);
	}

	@Override
	public  List<DocVO> viewDocList(int id) {
		
		return docMapper.viewDocList(id);
	}

	@Override
	public  List<DocVO> temporaryDocList(int id) {
		
		return docMapper.temporaryDocList(id);
	}
	
	@Override
	public DocVO docInfo(DocVO docVO) {
		return docMapper.docInfo(docVO);
	}

	@Override
	public TaskVO newTask(TaskVO taskVO) {
		return docMapper.newTask(taskVO);
	}

	@Override
	public List<TaskVO> category() {
		List<TaskVO> list = docMapper.category();
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setTaskList(docMapper.task(list.get(i).getCategory()));
		}
		
		return list;
	}

	@Override
	public List<TaskVO> task(String approvalTask) {
		return docMapper.task(approvalTask);
	}

	@Override
	public int docInsert(DocVO docVO) {
		
		return docMapper.docInsert(docVO);
	}

	@Override
	public int tempInsert(DocVO docVO) {
		int result = 0;
		
		result = docMapper.tempInsert(docVO);
		return result;
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
	public Map<String, Object> docDelete(List<Integer> docId) {
		Map<String, Object> result = new HashMap<>();
		try {
			docMapper.docDelete(docId);
			result.put("result", true);
		} catch (Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<EmpVO> allDept() {
		// 부서 목록
		List<EmpVO> list = docMapper.allDept();
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setEmpInfo(docMapper.deptEmp(list.get(i).getDepartmentId()));
		}
		
		return list;
	}

	@Override
	public List<EmpVO> deptEmp(String departmentId) {
		return docMapper.deptEmp(departmentId);
	}

	@Override
	public EmpVO empInfo(int id) {
		return docMapper.empInfo(id);
	}
	
	
}

