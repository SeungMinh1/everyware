package com.yedam.app.dataroom.dataroom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dataroom.dataroom.mapper.DataMapper;
import com.yedam.app.dataroom.dataroom.service.DataService;
import com.yedam.app.dataroom.dataroom.service.DataVO;
import com.yedam.app.dataroom.file.mapper.DataFileMapper;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	DataFileMapper dataFileMapper;

	//부서번호조회
	@Override
	public String selectDeptId(int empId) {
		return dataMapper.selectDeptId(empId);
	}
	
	//자료실 조회
	@Override
	public List<DataVO> dataList(DataVO dataVO, int page, int cnt, 
			String category, String remarks) {
		return dataMapper.selectDataList(dataVO, page, cnt, category, remarks);
	}
	
	//자료실 cnt
	@Override
	public int dataListCnt(DataVO dataVO, String category, String remarks) {
		return dataMapper.dataListCnt(dataVO, category, remarks);
	}
		
	//자료등록
	@Override
	public int insertData(DataVO dataVO) {
		dataMapper.insertData(dataVO);
		//첨부파일이 없을때 체크
		if(dataVO.getAttachList() != null && dataVO.getAttachList().size() > 0) {
			dataFileMapper.updateDataGroupId(dataVO);
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
	//dataIds로 파일삭제
	@Override
	public Map<String, Object> deleteFiles(List<Integer> dataIds) {
		Map<String, Object> result = new HashMap<>();
		try {
			dataMapper.deleteFiles(dataIds);
			result.put("result", true);
		} catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		
		return result;
	}
	
	
	
	

	
	
	
}
