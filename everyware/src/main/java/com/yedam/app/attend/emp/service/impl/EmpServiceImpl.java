package com.yedam.app.attend.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.emp.mapper.EmpMapper;
import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;


@Service
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	EmpMapper empMapper;

	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAll();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		if(result == 1) {
			return empVO.getEmpId();
		}else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = empMapper.updateEmpInfo(empVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", empVO);
		return map;
	}

	@Override
	public int empDelete(int empId) {
		int result = empMapper.deleteEmpInfo(empId);
		return result;

	}
	

}
