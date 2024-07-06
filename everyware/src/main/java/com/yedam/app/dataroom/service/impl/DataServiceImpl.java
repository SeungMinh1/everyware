package com.yedam.app.dataroom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attach.mapper.FileMapper;
import com.yedam.app.dataroom.mapper.DataMapper;
import com.yedam.app.dataroom.service.DataService;
import com.yedam.app.dataroom.service.DataVO;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	FileMapper fileMapper;

	//부서번호조회
	@Override
	public String selectDeptId(int empId) {
		return dataMapper.selectDeptId(empId);
	}
	
	//자료실 조회
	@Override
	public List<DataVO> dataListCommon(DataVO dataVO) {
		return dataMapper.selectDataCommon(dataVO);
	}

	@Override
	public List<DataVO> dataListDept(DataVO dataVO) {
		return dataMapper.selectDataDept(dataVO);
	}

	@Override
	public List<DataVO> dataListMe(DataVO dataVO) {
		return dataMapper.selectDataMe(dataVO);
	}

	//자료등록
	@Override
	public int insertData(DataVO dataVO) {
		dataMapper.insertData(dataVO);
		//첨부파일이 없을때 체크
		if(dataVO.getAttachList() != null && dataVO.getAttachList().size() > 0) {
			fileMapper.updateGroupId(dataVO);
		}
		return 1;
	}

	//자료조회
	@Override
	public List<DataVO> selectDataInfo(DataVO dataVO) {
		return dataMapper.selectData(dataVO);
	}

	//자료삭제
	@Override
	public Map<String, Object> deleteData(List<Integer> dataId) {
		Map<String, Object> result = new HashMap<>();
		try {
			dataMapper.deleteData(dataId);
			result.put("result", true);
		} catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		
		return result;
	}
	
	

	
	
	
}
