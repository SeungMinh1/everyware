package com.yedam.app.dataroom.dataroom.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.dataroom.dataroom.service.DataPageDTO;
import com.yedam.app.dataroom.dataroom.service.DataService;
import com.yedam.app.dataroom.dataroom.service.DataVO;
import com.yedam.app.dataroom.file.service.DataFileService;

@Controller
public class DataController {
	
	@Autowired
	private DataService dataService;
	@Autowired
	DataFileService dataFileService;
	
	//자료실 페이지 홈 
	@GetMapping("dataroom")
	public String dataroom() {
		return "dataroom/dataroom";
	}
	
	//자료실리스트
	@GetMapping("dataList")
	@ResponseBody
	public Map<String, Object> dataList(DataVO dataVO, 
							 Integer page, 
							 Integer cnt,
							 String category,
							 String remarks,
							 String searchType,
							 String searchKeyword) {
		    
		Integer empId = AuthUtil.getEmpId();
		String deptId = dataService.selectDeptId(empId);
		dataVO.setDepartmentId(deptId);
		dataVO.setEmpId(empId);
		dataVO.setRemarks(remarks);
		
		dataVO.setSearchType(searchType);
		dataVO.setSearchKeyword(searchKeyword);
		
		  // 디버깅 출력
	    System.out.println("Search Option: " + dataVO.getSearchType());
	    System.out.println("Do Search: " + dataVO.getSearchKeyword());
	    
		page = page == null ? 1 : page; 
		cnt = cnt == null ? 10 : cnt; 
		
		int allCount = dataService.dataListCnt(dataVO, category, remarks);
		DataPageDTO pg = new DataPageDTO(page, allCount, cnt);
		
		List<DataVO> find = dataService.dataList(dataVO, page, cnt, category, remarks);
		
		Map<String, Object> map = new HashMap<>();
		map.put("datas", find);
		map.put("pg", pg);
		
		return map;
	}
	/*
	// 개인 자료실 AJAX
	@GetMapping("dataMe")
	@ResponseBody
	public List<DataVO> dataMe(DataVO dataVO, Model model) {
		Integer empId = AuthUtil.getEmpId();
		dataVO.setEmpId(empId);
		dataVO.setRemarks("개인");
		
		//List<DataVO> find = dataService.dataListMe(dataVO);
		//model.addAttribute("datas", find);
		return  dataService.dataListMe(dataVO);
	}
	*/
	
	//자료등록
	@PostMapping("insertData")
	@ResponseBody
	public int insertData(@RequestBody DataVO dataVO) {
		Integer empId = AuthUtil.getEmpId();
		dataVO.setEmpId(empId);
		
		String deptId = dataService.selectDeptId(empId);
		dataVO.setDepartmentId(deptId);
		
		return dataService.insertData(dataVO);
	}
	
	//자료조회
	@GetMapping("selectData")
	@ResponseBody
	public List<DataVO> selectData(DataVO dataVO) {
		return dataService.selectDataInfo(dataVO);
	}
	
	//자료삭제(여러개)
	@PostMapping("deleteData")
	@ResponseBody
	public Map<String, Object> deleteData(@RequestBody List<Integer> dataId) {
		return dataService.deleteData(dataId);
	}
	
	//자료 여러개 삭제시 그 그룹아이디를 가진 파일 전체 삭제
	@PostMapping("deleteGroupIdFiles")
	@ResponseBody
	public Map<String, Object> deleteFiles(@RequestBody List<Integer> dataIds) {
		return dataService.deleteFiles(dataIds);
	}
	
	// 폴더 삭제시 프로시저 -> 파일 동시 삭제
	@PostMapping("deleteDataFolder")
	@ResponseBody
	public int deleteDataFolder(@RequestBody DataVO dataVO) {
		return dataService.deleteDataFolder(dataVO);
	}
}
